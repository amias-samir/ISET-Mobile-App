package np.com.naxa.iset.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.franmontiel.localechanger.LocaleChanger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import np.com.naxa.iset.R;
import np.com.naxa.iset.DatabaseDataSPClass;
import np.com.naxa.iset.database.databaserepository.CommonPlacesAttrbRepository;
import np.com.naxa.iset.database.entity.CommonPlacesAttrb;
import np.com.naxa.iset.database.entity.EducationalInstitutes;
import np.com.naxa.iset.database.entity.GeoJsonCategoryEntity;
import np.com.naxa.iset.database.entity.GeoJsonListEntity;
import np.com.naxa.iset.database.entity.HospitalFacilities;
import np.com.naxa.iset.database.entity.OpenSpace;
import np.com.naxa.iset.home.HomeActivity;
import np.com.naxa.iset.home.MapDataRepository;
import np.com.naxa.iset.network.model.GeoJsonCategoryDetails;
import np.com.naxa.iset.network.retrofit.NetworkApiClient;
import np.com.naxa.iset.network.retrofit.NetworkApiInterface;
import np.com.naxa.iset.utils.DialogFactory;
import np.com.naxa.iset.utils.SharedPreferenceUtils;
import np.com.naxa.iset.viewmodel.CommonPlacesAttribViewModel;
import np.com.naxa.iset.viewmodel.EducationalInstitutesViewModel;
import np.com.naxa.iset.viewmodel.GeoJsonCategoryViewModel;
import np.com.naxa.iset.viewmodel.GeoJsonListViewModel;
import np.com.naxa.iset.viewmodel.HospitalFacilitiesVewModel;
import np.com.naxa.iset.viewmodel.OpenSpaceViewModel;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private MapDataRepository repository;
    private CommonPlacesAttribViewModel commonPlacesAttribViewModel;
    private HospitalFacilitiesVewModel hospitalFacilitiesVewModel;
    private EducationalInstitutesViewModel educationalInstitutesViewModel;
    private OpenSpaceViewModel openSpaceViewModel;
    private DatabaseDataSPClass sharedpref = new DatabaseDataSPClass(this);

    private final int RESULT_STORAGE_PERMISSION = 50;

    private NetworkApiInterface apiInterface;
    GeoJsonCategoryViewModel geoJsonCategoryViewModel;
    GeoJsonListViewModel geoJsonListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);
        repository = new MapDataRepository();

        apiInterface = NetworkApiClient.getAPIClient().create(NetworkApiInterface.class);
        geoJsonCategoryViewModel = ViewModelProviders.of(this).get(GeoJsonCategoryViewModel.class);
        geoJsonListViewModel = ViewModelProviders.of(this).get(GeoJsonListViewModel.class);


        commonPlacesAttribViewModel = ViewModelProviders.of(this).get(CommonPlacesAttribViewModel.class);
        hospitalFacilitiesVewModel = ViewModelProviders.of(this).get(HospitalFacilitiesVewModel.class);
        educationalInstitutesViewModel = ViewModelProviders.of(this).get(EducationalInstitutesViewModel.class);
        openSpaceViewModel = ViewModelProviders.of(this).get(OpenSpaceViewModel.class);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LocaleChanger.setLocale(new Locale("ne", "NP"));

//            ActivityRecreationHelper.recreate(ReportActivity.this, true);
        }

        handleStoragePermission();

//        parseAndSaveGeoJSONPoints().subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Timber.i("Starting parser");
//            }
//
//            @Override
//            public void onNext(Long id) {
//                Timber.i("Row inserted at %s", id);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Timber.e("Parsing failed reason %s", e.getMessage());
//                e.printStackTrace();
//
//                ToastUtils.showToast("Error loading app");
//            }
//
//            @Override
//            public void onComplete() {
//                Timber.i("Parsing completed sucessfully");
//
//                HomeActivity.start(SplashActivity.this);
//
//            }
//        });


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG, "Permission is granted");
//                //File write logic here
//                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                finish();
//            } else {
//                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                finish();
//            }
//        }


    }


    @AfterPermissionGranted(RESULT_STORAGE_PERMISSION)
    private void handleStoragePermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            SharedPreferenceUtils sharedPreference = new SharedPreferenceUtils(this);

            Date date = Calendar.getInstance(new Locale("en", "US")).getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String formattedDate = df.format(date);

            if (formattedDate.equals(sharedPreference.getStringValue("time", ""))) {
                HomeActivity.start(this);
            } else {
                SharedPreferenceUtils.getInstance(this).setValue("time", formattedDate);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    fetchGeoJsonCategoryList();
                } else {
                    // redirect to homepage
                    HomeActivity.start(SplashActivity.this);
                }
            }


        } else {
            EasyPermissions.requestPermissions(this, "Provide storage permission to save data.",
                    RESULT_STORAGE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }


    private Function<Pair, ObservableSource<Pair>> readGeoJason(int position) {
        return pair -> {
            String assetName = (String) pair.first;
            String fileContent = (String) pair.second;
            Log.i(TAG, fileContent + "");
            saveGeoJsonDataToDatabase(position, fileContent);
            return repository.getGeoJsonString(position + 1);
        };
    }

    private void saveGeoJsonDataToDatabase(int pos, String geoJson) {
        if (pos == 0) {
//            save hospital data
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveHospitalData(geoJson);
                }
            }).start();
        }

        if (pos == 1) {
//            save openspace data
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveOpenSpaces(geoJson);
                }
            }).start();
        }

        if (pos == 2) {
//            save school data
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveEducationalInstitutes(geoJson);
                }
            }).start();
        }
    }

    private void saveHospitalData(String geoJsonString) {

        CommonPlacesAttrbRepository.pID.clear();
        JSONObject jsonObject = null;
        String name = null, address = null, remarks = null;

        String category = null, type = null, open_space = null, contact_no = null, contact_pe = null, emergency_service = null, icu_service = null,
                ward = null, ambulance = null, number_of_beds = null, structure_type = null, earthquake_damage = null, toilet_facility = null,
                fire_extingiusher = null, evacuation_plan = null, alternative_route = null, no_of_doctors = null, no_of_nurse = null,
                no_of_health_assistent = null, total_no_of_employees = null, water_storage = null, emergency_stock_capcity = null, ict_grading = null,
                No_of_Rooms = null, No_of_Stories = null, Emergency_Phone_Number = null, Male_Toilet = null, Female_Toilet = null,
                Differently_abled_Toilet_Facility = null, Disaster_Preparedness_Response_Plan = null, First_Aid_and_Emergency_Rescue = null,
                National_Building_Code = null, Building_Age_and_State = null, Occupancy = null, Area_in_Sq_m = null, Built_up_Area_in_Sq_m = null,
                Built_up_Area_in_Hectare = null, Area_in_Hectare = null, Open_Area_in_Sq_m = null, Open_Area_in_Hectare = null, Email = null,
                Web = null, Medicine_in_Stock = null, Blood_in_Stock = null;

        Long fk_common_places = null;
        Double latitude = 0.0, longitude = 0.0;
        try {
            jsonObject = new JSONObject(geoJsonString);
            JSONArray jsonarray = new JSONArray(jsonObject.getString("features"));

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject properties = new JSONObject(jsonarray.getJSONObject(i).getString("properties"));
                name = properties.getString("name");
                address = properties.getString("Address");
                latitude = Double.parseDouble(properties.getString("Y"));
                longitude = Double.parseDouble(properties.getString("X"));
                remarks = properties.getString("Remarks");

                CommonPlacesAttrb commonPlacesAttrb = new CommonPlacesAttrb(name, address, "hospital", latitude, longitude, remarks, properties.toString());

                fk_common_places = commonPlacesAttribViewModel.insert(commonPlacesAttrb);
                Log.d(TAG, "saveHospitalData: " + fk_common_places);

                ward = properties.getString("Ward no.").trim();
                category = properties.getString("Category").trim();
                type = properties.getString("Type").trim();
                open_space = properties.getString("Open_Space").trim();
                contact_no = properties.getString("Contact_Number").trim();
                contact_pe = properties.getString("Contact_Person").trim();
                emergency_service = properties.getString("Emergency_Service").trim();
                icu_service = properties.getString("ICU_Service").trim();
                ambulance = properties.getString("Ambulance_Service").trim();
                number_of_beds = properties.getString("Number_of_Beds").trim();
                structure_type = properties.getString("Structure_Type").trim();
                earthquake_damage = properties.getString("Earthquake_Damage").trim();
                toilet_facility = properties.getString("Toilet_Facility").trim();
                fire_extingiusher = properties.getString("Fire_Extinguisher").trim();
                evacuation_plan = properties.getString("Evacuation_Plan").trim();
                alternative_route = properties.getString("Alternatice_Route").trim();
                no_of_doctors = properties.getString("No_of_Doctors").trim();
                no_of_nurse = properties.getString("No_of_Nurses").trim();
                no_of_health_assistent = properties.getString("No_of_Health_Assistant").trim();
                total_no_of_employees = properties.getString("Total_No_of_Employees").trim();
                water_storage = properties.getString("Water_Storage_Capacity_Litre_").trim();
                emergency_stock_capcity = properties.getString("Emergency_Stock_Capacity").trim();
                ict_grading = properties.getString("ICT_Grading_A_B_C_D").trim();

                No_of_Rooms = properties.getString("No_of_Rooms").trim();
                No_of_Stories = properties.getString("No_of_Stories").trim();
                Emergency_Phone_Number = properties.getString("Emergency_Phone_Number").trim();
                Male_Toilet = properties.getString("Male_Toilet").trim();
                Female_Toilet = properties.getString("Female_Toilet").trim();
                Differently_abled_Toilet_Facility = properties.getString("Differently_abled_Toilet_Facility").trim();
                Disaster_Preparedness_Response_Plan = properties.getString("Disaster_Preparedness_Response_Plan").trim();
                First_Aid_and_Emergency_Rescue = properties.getString("First_Aid_and_Emergency_Rescue").trim();
                National_Building_Code = properties.getString("National_Building_Code").trim();
                Building_Age_and_State = properties.getString("Building_Age_and_State").trim();
                Occupancy = properties.getString("Occupancy").trim();
                Area_in_Sq_m = properties.getString("Area_in_Sq_m").trim();
                Built_up_Area_in_Sq_m = properties.getString("Built_up_Area_in_Sq_m").trim();
                Built_up_Area_in_Hectare = properties.getString("Built_up_Area_in_Hectare").trim();
                Area_in_Hectare = properties.getString("Area_in_Hectare").trim();
                Open_Area_in_Sq_m = properties.getString("Open_Area_in_Sq_m").trim();
                Open_Area_in_Hectare = properties.getString("Open_Area_in_Hectare").trim();
                Email = properties.getString("Email").trim();
                Web = properties.getString("Web").trim();
                Medicine_in_Stock = properties.getString("Medicine_in_Stock").trim();
                Blood_in_Stock = properties.getString("Blood_in_Stock").trim();

                HospitalFacilities hospitalFacilities = new HospitalFacilities(fk_common_places, ward, category, type, open_space, contact_no,
                        contact_pe, emergency_service, icu_service, ambulance, number_of_beds, structure_type, earthquake_damage, toilet_facility,
                        fire_extingiusher, evacuation_plan, alternative_route, no_of_doctors, no_of_nurse, no_of_health_assistent, total_no_of_employees,
                        water_storage, emergency_stock_capcity, ict_grading, No_of_Rooms, No_of_Stories, Emergency_Phone_Number, Male_Toilet,
                        Female_Toilet, Differently_abled_Toilet_Facility, Disaster_Preparedness_Response_Plan, First_Aid_and_Emergency_Rescue,
                        National_Building_Code, Building_Age_and_State, Occupancy, Area_in_Sq_m, Built_up_Area_in_Sq_m, Built_up_Area_in_Hectare,
                        Area_in_Hectare, Open_Area_in_Sq_m, Open_Area_in_Hectare, Email, Web, Medicine_in_Stock, Blood_in_Stock);

                hospitalFacilitiesVewModel.insert(hospitalFacilities);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void saveEducationalInstitutes(String geoJsonString) {

        CommonPlacesAttrbRepository.pID.clear();

        JSONObject jsonObject = null;
        Double latitude = 0.0, longitude = 0.0;
        String name = null, address = null, type = null, remarks = null;

        Long fk_common_places = null;
        String college_he = null, contact_no = null, contact_pe = null, earthquake = null, evacuation = null, fire_extin = null,
                female_student = null, male_student = null, total_student = null, level = null, structure = null, open_space = null;
        try {
            jsonObject = new JSONObject(geoJsonString);
            JSONArray jsonarray = new JSONArray(jsonObject.getString("features"));

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject properties = new JSONObject(jsonarray.getJSONObject(i).getString("properties"));
                name = properties.getString("name");
                address = properties.getString("Address");
                type = properties.getString("Type");
                latitude = Double.parseDouble(properties.getString("Y"));
                longitude = Double.parseDouble(properties.getString("X"));
                remarks = properties.getString("Remarks");

                CommonPlacesAttrb commonPlacesAttrb = new CommonPlacesAttrb(name, address, type, latitude, longitude, remarks, properties.toString());

                fk_common_places = commonPlacesAttribViewModel.insert(commonPlacesAttrb);
                Log.d(TAG, "saveEducationalInstitutes: " + fk_common_places);

                college_he = properties.getString("College_He");
                contact_no = properties.getString("Contact_Nu");
                contact_pe = properties.getString("Contact_Pe");
                earthquake = properties.getString("Earthquake");
                evacuation = properties.getString("Evacuation");
                fire_extin = properties.getString("Fire_Extin");
                male_student = properties.getString("Male_Stude");
                female_student = properties.getString("Female_Stu");
                total_student = properties.getString("Total_Stud");
                open_space = properties.getString("Open_Space");
                structure = properties.getString("Structure_");

                EducationalInstitutes educationalInstitutes = new EducationalInstitutes(fk_common_places, college_he, contact_no, contact_pe, earthquake,
                        evacuation, female_student, male_student, total_student, fire_extin, level, open_space, structure);
                educationalInstitutesViewModel.insert(educationalInstitutes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveOpenSpaces(String geoJsonString) {
        CommonPlacesAttrbRepository.pID.clear();

        JSONObject jsonObject = null;
        Double latitude = 0.0, longitude = 0.0;
        String name = null, address = null, type = null, remarks = null;

        Long fk_common_places = null;
        String access_roa = null, accommodat = null, area_sqm = null, hign_tensi = null, road_access = null, shape_area = null,
                shape_leng = null, terrain_ty = null, toilet_fac = null, water_faci = null, wifi_facil = null;
        try {
            jsonObject = new JSONObject(geoJsonString);
            JSONArray jsonarray = new JSONArray(jsonObject.getString("features"));

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject properties = new JSONObject(jsonarray.getJSONObject(i).getString("properties"));
                name = properties.getString("name");
                address = properties.getString("Address");
                type = properties.getString("Type");
                latitude = Double.parseDouble(properties.getString("Y"));
                longitude = Double.parseDouble(properties.getString("X"));
                remarks = properties.getString("Remarks");

                CommonPlacesAttrb commonPlacesAttrb = new CommonPlacesAttrb(name, address, type, latitude, longitude, remarks, properties.toString());

                fk_common_places = commonPlacesAttribViewModel.insert(commonPlacesAttrb);
                Log.d(TAG, "saveOpenSpaces: " + fk_common_places);

                access_roa = properties.getString("Access_Roa");
                accommodat = properties.getString("Accommodat");
                area_sqm = properties.getString("Area_SqM");
                hign_tensi = properties.getString("High_Tensi");
                road_access = properties.getString("Road_Acces");
                shape_area = properties.getString("Shape_Area");
                shape_leng = properties.getString("Shape_Leng");
                terrain_ty = properties.getString("Terrain_Ty");
                toilet_fac = properties.getString("Toilet_Fac");
                water_faci = properties.getString("Water_Faci");
                wifi_facil = properties.getString("Wifi_Facil");

                OpenSpace openSpace = new OpenSpace(fk_common_places, access_roa, accommodat, area_sqm, hign_tensi, road_access, shape_area,
                        shape_leng, terrain_ty, toilet_fac, water_faci, wifi_facil);
                openSpaceViewModel.insert(openSpace);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    ProgressDialog progressDialog;
    int[] totalCount = new int[1];
    int[] progress = new int[1];
    String[] geoJsonDisplayName = new String[1];
    private void fetchGeoJsonCategoryList() {
        progressDialog = DialogFactory.createProgressBarDialog(SplashActivity.this, "", "");
        progressDialog.show();



        final String[] geoJsonName = new String[1];
        final String[] summaryName = new String[1];
        final String[] geoJsonBaseType = new String[1];

        apiInterface
                .getGeoJsonCategoryDetails()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<GeoJsonCategoryDetails, ObservableSource<List<GeoJsonCategoryEntity>>>() {
                    @Override
                    public ObservableSource<List<GeoJsonCategoryEntity>> apply(GeoJsonCategoryDetails geoJsonCategoryDetails) throws Exception {
                        totalCount[0] = geoJsonCategoryDetails.getData().size();
                        progressDialog.setMax(totalCount[0]);

                        return Observable.just(geoJsonCategoryDetails.getData());
                    }
                })
                .flatMapIterable(new Function<List<GeoJsonCategoryEntity>, Iterable<GeoJsonCategoryEntity>>() {
                    @Override
                    public Iterable<GeoJsonCategoryEntity> apply(List<GeoJsonCategoryEntity> geoJsonCategoryEntities) throws Exception {
                        return geoJsonCategoryEntities;
                    }
                })
                .flatMap(new Function<GeoJsonCategoryEntity, Observable<ResponseBody>>() {
                    @Override
                    public Observable<ResponseBody> apply(GeoJsonCategoryEntity geoJsonCategoryEntity) throws Exception {

                        geoJsonCategoryViewModel.insert(geoJsonCategoryEntity);
                        geoJsonDisplayName[0] = geoJsonCategoryEntity.getCategoryName();
                        geoJsonName[0] = geoJsonCategoryEntity.getCategoryTable();
                        geoJsonBaseType[0] = geoJsonCategoryEntity.getCategoryType();
                        summaryName[0] = geoJsonCategoryEntity.getSummaryName();

                        return apiInterface.getGeoJsonDetails(geoJsonCategoryEntity.getCategoryTable());
                    }

                })
                .subscribe(new DisposableObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody s) {


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress[0]++;

                                String alertMsg = getString(R.string.fetching_file, geoJsonDisplayName[0], String.valueOf(progress[0]), String.valueOf(totalCount[0]));
                                progressDialog.setMax(totalCount[0]);
                                progressDialog.setMessage(alertMsg);
                                progressDialog.setProgress(progress[0]);
                            }
                        });

                        BufferedReader reader = new BufferedReader(new InputStreamReader(s.byteStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        try {

                            while ((line = reader.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            reader.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String geoJsonToString = sb.toString();
                        Log.d(TAG, "onNext: GeoJson " + sb.toString());

                        if (!TextUtils.isEmpty(geoJsonToString)) {
                            geoJsonListViewModel.insert(new GeoJsonListEntity(geoJsonName[0], geoJsonToString));

//                            json parse and store to database
                            JSONObject jsonObject = null;
                            try {
                                int latlongDiffCounter = 0;

                                jsonObject = new JSONObject(geoJsonToString);

                                JSONArray jsonarray = new JSONArray(jsonObject.getString("features"));
                                Log.d(TAG, "onNext: " + "save data to database --> " + geoJsonName[0] + " , " + geoJsonBaseType[0]);

                                for (int i = 0; i < jsonarray.length(); i++) {
                                    JSONObject properties = new JSONObject(jsonarray.getJSONObject(i).getString("properties"));
                                    JSONObject geometry = new JSONObject(jsonarray.getJSONObject(i).getString("geometry"));
                                    JSONArray coordinates = geometry.getJSONArray("coordinates");

                                    String name = properties.has(summaryName[0]) ? properties.getString(summaryName[0])
                                            : properties.has("name") ? properties.getString("name")
                                            : properties.has("Name") ? properties.getString("Name")
                                            : properties.has("Name of Bank Providing ATM Service") ? properties.getString("Name of Bank Providing ATM Service")
                                            : "null";

                                    String address = properties.has("address") ? properties.getString("address") : properties.has("Address") ? properties.getString("Address") : " ";

                                    String type = geometry.getString("type");


                                    double longitude;
                                    double latitude;
                                    double latlongMakeDiff = Double.parseDouble("0.0000000000"+latlongDiffCounter+i);
                                    if (type.equals("Point")) {
                                        longitude = Double.parseDouble(coordinates.get(0).toString()) + latlongMakeDiff;
                                        latitude = Double.parseDouble(coordinates.get(1).toString()) + latlongMakeDiff;
                                    } else if (type.equals("MultiPolygon")) {
                                        JSONArray coordinates1 = coordinates.getJSONArray(0);
                                        JSONArray coordinates2 = coordinates1.getJSONArray(0);
                                        JSONArray coordinates3 = coordinates2.getJSONArray(0);

                                        longitude = Double.parseDouble(coordinates3.get(0).toString()) + latlongMakeDiff;
                                        latitude = Double.parseDouble(coordinates3.get(1).toString()) + latlongMakeDiff;
                                    } else {
// for multiLineString
                                        JSONArray coordinates1 = coordinates.getJSONArray(0);
                                        JSONArray coordinates2 = coordinates1.getJSONArray(0);

                                        longitude = Double.parseDouble(coordinates2.get(0).toString()) + latlongMakeDiff;
                                        latitude = Double.parseDouble(coordinates2.get(1).toString()) + latlongMakeDiff;
                                    }


                                    latlongDiffCounter++;
                                    if(latlongDiffCounter > 9){
                                        latlongDiffCounter = 0;
                                    }


                                    CommonPlacesAttrb commonPlacesAttrb = new CommonPlacesAttrb(name, address, geoJsonName[0], latitude, longitude, summaryName[0], properties.toString());
                                    long id = commonPlacesAttribViewModel.insert(commonPlacesAttrb);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        finish();
                        Log.d(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        HomeActivity.start(SplashActivity.this);
//                        new Handler().postDelayed(() -> {
//                        }, 5000);
                    }
                });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }
}
