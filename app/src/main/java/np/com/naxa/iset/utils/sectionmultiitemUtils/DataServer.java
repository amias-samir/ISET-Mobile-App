package np.com.naxa.iset.utils.sectionmultiitemUtils;

import java.util.ArrayList;
import java.util.List;

public class DataServer {

    public static List<SectionMultipleItem> getSectionMultiData() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, false));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Counts", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Information", false, false));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));

        return list;
    }


    public static List<SectionMultipleItem> getThingsToDoBefore() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data

        list.add(new SectionMultipleItem(true, "Always be ready with emergency kit", false, true));
        // add multiple type item data ---start---

        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzVmAv8RpDE5LX-5TnDHICNSaEnNL1TuDra4UczpDKiXvWsULP", "Bag Pack", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://media.istockphoto.com/photos/referee-whistle-picture-id520288272?k=6&m=520288272&s=612x612&w=0&h=pwECJTMFoVYt56JkZevfGJb3CirlP7t05MkUoOLPk1s=",  "Whistle", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxASEhISEhIWFRUXGBYVGBURGBYXEhUYFxgXFxgVFRcaHSggGBslGxYVITEhJikrLi4uFyAzOzMsNygtLisBCgoKDg0OGhAQGzcmICUtLi0tLS0uLS0vLS0tLTEuLS0tLi42MC0yLS0tLSstLS0tLy0tLS01LS0tLS0tLS0rLf/AABEIALEBHAMBIgACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAABQYDBAcCAQj/xABMEAACAQIDAwcGCwYEAwkAAAABAgADEQQSIQUxQQYTIlFhcYEHIzJicpEUM0JSc4KSobHB0UNTk6KywhUks9I0ROIWVGNkdIOEtPH/xAAaAQEAAwEBAQAAAAAAAAAAAAAAAQIDBgQF/8QAMxEAAgECAwQKAQQCAwAAAAAAAAECAxEEITESkbHRBRMiMkFRYXGBofAVM0LhQ8EUI/H/2gAMAwEAAhEDEQA/AO4xEQBERAEREAREQBERAEREAREQBERAEREARE81KgUXJAHWYB6iai44N8WC/aNE+0dPdPvMO3ptYfNTQeLbz90m3mDNUrKNL69Q1PuEi+UODxVeg6Yer8HqXUrU3m6sDZgPkm1jY6iStOkqiygAdk9wDygNhc3NtTuueu09REgCIiAIiIAiIgCIiAIiIAiIgCIiAIiIAiIgCQe1OU+HovzZa7XAsCBqeHfJyfnnblRm2hX1Or4v7krW/AS8Ip6kM7DhOUWKrKKlHAs1M+iz1VQsPnBStwDwlM2r5RMWKlSmjrSZHZCgweIrMpUkEFg4DEdYFjvnVaSBVCjQAADuGk9ytyTjR8qe11JH+GFwCQGFKuuYA6NlOq3GtjuvPa+VvaI9LZb+6qPxWdiiVzL3h5Pf/RyJfLBiR6WzKnvb81mRfLK3ytnVR9b/AKZ1StXRASzAAC5J3AdZ7JCPyqoucuHDV2O40x5vvz7mHXlzEdUZ6sLZeie/+ilr5Z6fHA1h9YfpMy+WXD8cHXH2ZbRgsZX1qstFfmUtW7i+894yEdUkNn7GoUR0UufnNqxJ3k9u7XfpIuybQKBiPLDhyOhRqqetlU/3Ca9Pyl7OJvWTE1T6yqEHcgb8bzqZw6fMX3CeGwNE76SHvVf0lk2V7JQ6fle2bYDJXAGlgi2H80zp5WtmH98O+n+hlwbZOGO+hSPfTT9J4Ow8Id+Go/wk/SQOyVlPKlss/tKg76bTYTyk7KP7cjvp1P8AbJs8nsCf+Uofwqf6Tz/2awH/AHPD/wAGn/tkkO3gauz+Wez67rTp4gFmvYFXW9gTvKgbgZOo4O4g905p5WdkYehhaVShRp0m54KTSRVuDTqGxsNdVHumv5FNpVar4um7XVFpEDtY1ATbdwG6W2Xa4ys8zqsREqQIiIAiIgCIiAIiIAiIgCIiAIiIAiIgCfnl1LbRPa+I/mSqPzn6Gn562U98eD1k/wAzqv8AdNafiQz9Cz4TBM5zt3Hc5WxVB6zAHECloxsqHCB1CjcLVGV7778ZmiyVy3bZ5UYPC2FWqAx9Gmt2qt7NNQWbvAMgG5S47FErhMPza/Pr9J/4aMAum4u6+zwmfk7yMwmHBZ3Wq7aswsocneTqWbxYjslpp1KSAKuVQNwXQDuAmO1N6Zfb5cTXZgvV/XPgVjDckGqEPjKzVjocrEMoI1BVLCmh7Ql/WlnweBp0ly01CjjvLHvY6nxn34WnzvuM+HGp1n3GSlFZt3f5+ZEPbeVjYiavw5e2Pho6j90ttx8yvVy8jaic+XlRtFsbjKPNWo02pikwQguGUliWY2axA3dcm6O0q59JyO/mh/bLXja90T1U/Is0Sm4baWIzPzlUZc7ZbsAct+j6I6o2nj3ZLU64VsyG4dzoGBYfZuPGZOvQWtRbyyw1V6RLlEpo2kALHEE+FQ/i02qPKZEULctbjl/6plLHYaP+RP2LrB1n/FkR5aWAwNIn9+n+nVlb8g7g18db5lH+qpLByv2j8MorSUKtnD3qC40DCwF/W65p8ka9PBO5Lo5cBcqBae436zeZfqtPaUFp558LHoXR0urcn3vBf2dPia2AxqVlzL3EHeD1GbM9sZKSvHQ+fKLi7MRESxAiIgCIiAIiIAiIgCIiAIiIAiIgHwmfnXk6GbGC2+yn3VaRJ9153PlZiTTwtUrvNl8GIB+684pyOa2Mqt83C129xpj85Dm1CdtUuZpGHdfm7cOZ1B9pk8bzVGHpFi+UBiQxIA9ICwbdvsLdo33lc2FXWwzMzm2YkEBFA38b2HXJZdpLzbVEHysiZuLWuWt1C48SJyGKrYiF3Gp6HRPCLwj+MkcVtU0tM5J+aQn5Lp4maFTlZUHyV7zp+chaNBqxZi5Wmps773ZjrkS+9tRcndcbyQJX8fytwVJitDDCsdxeo5Knuc3z+CheomerB4fGVo3dRmVaphcP2ZR2nuLjU5YVPnIPdNduVdY7qn2V/QSmHlzUHxeEwyHrZMx94yn74PL3H8DSXtRD/cxnv/S6z71V72eb9Soru0kXL/GsW3o88fYV/wAhPgxGOb9nWPtafiZQ6vK7aLb8S3gtNfwW806u18W/pYquew1amX7Oa33Sy6Hi+9Nsq+lX/Gml8HSvgmPbfSIHW9QD9ZhqUqi/GYjC0/bri/uNpzTm2qEA5nPAG7Hr0G/drNrB7Pd2KKnSFyRoCLEA3va2pl10NhvFXM30tX8LL4L22Ioj0toYcfRI9X+kmYztLBD/AJ2q30VDL/qASoYLBmoGKlTlt0b9Nr8EUasZM4Tkvi3QMuHqkk+iaTqO/MwAm0ejMNH+JlLpHEP+RJnbOB/84/e1OmP5TMX+NYfhhGP0uJqOPs5QJ6p8lXHNu7pTpsoYtWq0qZN92UZiRw3iR23hSwSlyaWKBDDJSqsFpksoBaplW5sW03fdN44WhHSKMZYms9ZM2xt5L2XC4QHqKM7fe/5TxtDlFieacIKC2BOUUKRQ21ykMp0O6QPIHYOExdSrVqZkBqNZRTVqSpdWyFr9AnpC9r2Isd8uHK3BZaTEAZAGVbehw9DqFriw4kX1E3jTSWSMnOT1ZbvJnjzVp5iAuZLsqXyBlNjlBJIXW4Fza9uEvE5z5Ij5oey34pOjTGnFQ2orS7L1ZOTTetkIiJoZCIiAIiIAiIgCIiAIiIAiIgCIiAUPyqbaelSShTtd7uzEXyqp0AHWx49SnrBHK9iF6LV6x6S1KFWgApGYM7U2uSx00X7xOjeU3Zr1KrVdAi06SszaKoz1dT1+luFzpKFyi2hhKCU1pEuNxZtAx13Dq3b5eUV1b9S0JNSXoyS5FYSrWp1qa6sFCE9RLBr24AgEbzJbEKadKkjCxUVLjt5xgf6R7pB+SXlCqYvEswsj01+0rAD+Un3Sx8p8QKjF13MHI8Xecl0hSjFxzzvmvhnWYevOpJK3Z1T9UmiA5aYxqWz6SKbc5lQkddVTVdvFc6HsI6pzcvlW/uHXL/5RRbB4QevR/wDr1Jz7EoSmnA3/ABnV04pRSRylVtzbZv7A2DXxjEioUUAnQEjT2dy30vqeyXzZnI/DMpXzhbgzPbXsFjlHfm/KV/kftFlwr82xBRlJsPRBcahx1sUNu8zpPJutzzA1NDb0lFrn1h+Y++bKKWZnc55iqfwI1KTUKdbMGKvWXpruUAWPRKkN35gd1pI4TCbHpWqfCsVXamVe9GilMXDCxtWvpe3XJHllst8TXYUWp5UJLVHqIqDOEVbkn/w2bu1mtR5RUEplqOEwFNhcAVKb16rFdxDMRa51BI/SUepJ7TaOz3qI9LAVq9R/l1cQ6surU7slBbfJOmmhHXJPDLjnUNh9jUKZJbWvQOcDokNnrML3Jbh8ntkLW5cYtqVvhNRHN+hRSjTor0jazKuf0bcd5MicVtZ66hGNR3uCWerVqs2hB6LE2uTfSUaCL5ia+0lzLU2hhsML9Fecoo4XpfJpKST6PHgZo1qNKozLV2jiK5IByUqddrDrV6rBbHXhwlcFDFVSrLQyWvbKgpqL7/S375I4fk7jK5zFsxOhy56jW7QotbxtMXiaKy2lf0z4GqoVHnbflxM9OpgEpsxw9So2mdatenSBZbqQi0wWIuT3zBtqng8T5tgtJCrZvg4qVHZiVtdqhGo1N+zcbyVw/IixtVqWPUzIhP1bs/3SYockcMgDODbiXBA99ZkHuUx19+7Fv645/RZ0l4yXHhzObcmMK2CrMVqGpSa4yEAVMvDXW7DTda5lh2xWr1kZKaVch/eKKa/zGx1trfh3k3uhsqig6FMW6xmK+9BTT+czTxNMG5RQeF6YDEfWpU6jDxqr3yVOq/Jb3yIappeL+uZ48lFB6aBXFmCNuII9JeI0PhOiSm8ivjW9ht5JPpLvJJJ9575cohe8r+ZFW2VvIRETQyEREAREQBERAEREAREQBERAEREA595XQ4oowvkYhG6gy5mS/eDU9w65xynsF8VZiwCIx3m1zYfh+c/QvLekrYR1YAglLg7vSnKsRhKaiyKAOz9Z5MVjOqWwlmfSwGB697TeRA8l9mpTqVbuAqkKD84gXNu4m3hLZjyOaS27m2t3ZnkKqASXxnxFP6I/i85vFz6ypGXqdTGiqVOMV68GRPlLNsNhx1Nh/wDQrfpKNQM6nyk2EcYUpBKj5Vwz2pFQfiqq9ItoBr1iYcFyKwiHK7YcNxXnHxNYd9JMw906qddx7Ki3ut9s41UVLtSklvb3I5/s2oKVTnKYAcgqRa6sCCCrJuINzLhS2xiWTJRwrJfS7OQnuIViPrHtvLphOTtFFuKVYr87JSwtHxFUlx7ps4ehQ3U0oHsprXx5v36Ip79BK9biHokt75cSdihHxb3Lm/o5lT5NYms5LspY78t3qdwVR9wkxS5BMq56zMi8WqmnQQd/OG8uG19rvQ6DhkB3fC8Vh8DSN+CLQvVPda8gRQLk1adIMf3mHwjVHXjf4ZtJwhXtCyOqqy7038WXN/ZHW0492C+bvkvobK5K4Jxem9Otra+HWri1v1Fhamp+tJ6hs3D0yFygH93Uq06bH2aVAO/hcSu1avPHKanPkdEKa+Jx+vEPhcEqYdD7TWkrszaTU25l2NMm1qTPQwrgWt5vDYTnK5BPz2vrIeFp6yV/e743H/JqaJ29suBPCgiDNkCDg5pJTt3vimLe5JmzM4vq44/G1qZHeeaoj75jp08hBsEY7mKpRL9mapzlcnuAmZ6Q0Zhv3NUGoPY+JJP2ac1SSVkjNtvNnyg1xlQm3VSO49RXDKAPGpPtIdLo2zbiEyh+882KlT3uJldLgF927zlyveDWKp9mmZkK6DNu3dK+TwD5E9yGSQazJc9bDrs1T7+eqD3LIvaiZrqemw1Ct5xx3K4ruPCkvhJyqtgAd26zaKe5WyqfCm0h9qL0ch4jRG3HupsAD4UGhEGzyNHnW9ht9wfSXrAP3DulxlO5GLaoRa3QbS1rdJdLZVt9le4S4xDWXv8A6RM/D2EREuZiIiAIiIAiIgCIiAIiIAiIgCIiAQXLQ/5VvaT8Zy3FmdQ5bn/Kt7SfjOXYmfF6S7/wdL0Mv+v55Gk0k8b8Qn0X+6RjSUxvxKfQ/kZ8Wec4e592v3V88CWxdOm1S1QUD0MNYYlajrcJU9GkmtRuyT2HWqF6PPhLfs6dHA4cfb86PCR+HFTn35vn/QoBvg5oroFqfGPU1Re1Olfsm5SWmW05lnBIuOe2hXH1z0aZ79BOyZwT1Z5opTYgqKLt101rY+p/Heyqe+a+38caarTqvYvcZcZjFw7W9WhhBep3CSGJqEWFZmHUMXXWlcerh8N6Y7DK+aj9KqmenTObp0KFHZ2Htwz4jEXrXtxUdfZCIZq4TD80uekhpJqWqYbDUcFS041MTjS1U+0o4T4lAVjnCLXYdK6LX2k47UxGJKYZfdaZaNEOecpoHYAedoU2xlVR/wCtxpFG3cp3eE9MBXNjbEEcC1XaDAjqVcmFokdpI0lihiY875svzttMjVamJ1+a+DwCpQX6z21mOm3NHmlJp7r0FZKDADcRhNnq9dwSNz1BfjNsk1PNFuc4GkWavb1XwmCCUEPtue2YkJ+JUntoqdV6lbCbPAFvpa0Elr2d0RltkbiigUmPrc3Sz1j9Zx2zbXokgaNxA6DsOuyZ6x8Ssj9iLakqKLAC3NqFAXTVWo4c5F7nqMeubqtpYaqOC2KjrDLTy0x9ZzMy5kU2Y29LjbRz2kLnq+9lnqm2ptv45fS+tkLN9qos18wy8Mn1eb/so/1+M9ltwPhfd9UMtvsUz3wDKzWBI0HEiwHiykD7VQyF2hoCNwbhoFfTq6Cv9mtJOrU143HeWA7Tq6jvNOQuOqixN7BtLg6N2XBAqd2et7MlEEhyLW1S1rWRtLZbdJeGRLd2Ve4S5Sk8hl85usAjbha12WwtlW32V7hLteI6y9/9IT8PYRES5mIiIAiIgCIiAIiIAiIgCIiAIiIBX+XP/Ct7SfjOXYidQ5df8K3tJ+M5dXM+L0l3/g6boX9r55Go0lMZ8Un0Q/pMi2kpjPi0+iX+mfGf7kPc+3iO5v4E+6IazZxSOlKwrU3rajOLpSTV2/DxkzWZgo5wuE4c/UTCUe5EpdNh6rSPpB+dqEc5ay5ilRKKAAvbnap6SD2e2/Cb+GXe1IC5tdsLTDsfaxVfouO0azsJanCGBsyUqhpBlWzXOGp08LT14tXxF2+ssr2Eph2z0wtRwPjMOrY6uvEk43FEUAO4SexSBw+ULUcBiMgOOrXHzWqWoodPRMhmAqMEfzrgjoVmOMrC3VhaGXD0Gvb0jaTErI+lRWbcK7Kb2u+0KikcVHQwtA9huJ6bzp5s+eI303JxRHHXDYfJhqLe0xnqp0zzb+cP7qqefcdhweFtRpHtdj2w3T80fOEfsWtWI7DhMLloUyOuo57ZYqeD5y9L4y2hpHz1h1NhcLkw9M2/eOe28wN5xTTHnVF81MZaygfNbD4c08LTIH72o3beZXbnAU+NC76fRr5QNdaNEphKBGmtR23T3gkFYqSy1EQ3zMRWpqQdLNZMHRa/BFqNpIBYMIPNqDYqoAv0Gprbdb0MOluoBvGbDtexP1S2ov6rVBb+HTMwhtzHf8liTc9YV3GY91OmO+esxBO/NbX0g57WsTVIt89kHZMzQyEnNxzW9bnLdl81W3hTG6eDUABN9PlEEAfWIbL9uox7JhL3BUC41JAClRxuwHmx3nnJF47bVNdc+cj92b276zaKPowR2SJSjHOTsaU6M6jtFXNzF4tVsD9UEf0rl+9aQ9qQe1MUSSHbm72zAk8+R1FAxYf+7UK9nCeaQxeIuKKc2p3lbpe/XUPTfwspkzsfkUiWaqcxGttyA+zx8byinKfdXy+Rq6VOl+5K78lz/wDTe5ElVp3X5VgONlX0RfjvJ8ZbVaR2HwCp6It3bpvos9EYqKsjxTltSuZgZ9nkT1JKiIiAIiIAiIgCIiAIiIAiIgCIiAV3l4f8ofbT8Zy2sZ1nllhGq4Vwu8FW8AdfunI69wSDoZ8bpKL2r+h0nQsl1bXqYWkpjfi1+iX+mRLGSuO9Bfo0/pE+N/lh7n3MR3Ph8CzqgNc6KWDdG9Fq1Qel8UB0UO7pNpukpWGY5anSbTo4gmtUH/xqPQTvmiulSrc2Gcg5qppUz2MF6VQ6+iO2SVMZUHyU4W/ytA9ij41z2HfOulqcKYMSL9Cpr6lc3PZbCYfQjtYyCx9LmzzL6KdEpVehTIIFgmBwgz1QL73aWFhlUW6Cndb/AC1I9w1rVD2cZrVqQy5T0Fbgt8OrC9zlRL16t+IJEJkNEJU4UWFuAoVBbTqGAwupHUar98x1Ol5mxa37BgKhUdRweGIo09ONaoe2SR2UVHNrZQd1EA0qVjxahRPO1h9I4HdPS7GXKEezIN1Nwoorb5uGpWpXHrs5lroixEqnO2HxgQj93XWmb6HKMuCw5B+kbTjLDhMORY3Zjwd2ueohajLoOyjTA7Zgxm0sPQsHcZh6K+m49hALUx3Be+QtflJWqtloplJ+U3TqnqNvRXvObvmNSvGOT3I9VHCVJratZebyX57FmrVkpguzBQdCxJRW72ualTuzfVkFitvL6NJC/VnBSlffdaS2Zj35T3z1geTFaq3OVnNzxY5qluq50A7NZadm7Bo0ty69Z1J8TM06s9Oyvs1bw1HXtPcufAqdPZeMxVs5ITeAwAQdq01sPE6yxbM5K0UszDOw4trbuG4eEsKUgN0yqs1hh4xzebPPVxlSassl5LIwUsOBuEzqkyBZ6Am55DwFnsCfZ9gHyfYiAIiIAiIgCIiAIiIAiIgCIiAIifCYB8Yic85Z7EpFs1IgMfkdfYP0l2xlQ2ld2hg8wJO8/dMq1OM4WkejDVZ0qilBnMHBBIOhElsb6I9in/Ss2ts7NLe3wPz+w+t+Pfv0sU24eom/ryj85zVei6NaF9LnX08QsTS7OtnkXemDz9bLmuHbWmilgMqE+cfo011149W6bdFt7LqbatTOdreviqmlvZFxKpgNuU6znMVRyQzCsGZA9gGOUEDUKpVjcA3Btpebx206NMBnqrUtuJZWAPqKPNp7p0zz7S0OQ2XtbNs/I31O9l46FqZ39jYmpq31BMZslyWy3383dSfaqHzjHxUGVDaHLViSKKkndma+7x1t6ug6jI6lhMbiz0sxU8Donu+V437553XjpHN/W89kcDJK9V7K9dd3OxZcdyqw9IFKYz+rSACX9Y7j4liJAvtfHYo5aQKrutSuPtVDr7rd0sOyORCCxq9Ls+T7uPjLdhNnJTACqAOwQqdSfedl5L85ESxFCjlSjd+bz+tOJRtk8iWOtZu3KunvO8/dLjs3YlGiLIgHhJVKUyKk3hRjDQ8dXEVKrvJmNKcyhJ7Cz2BNTA8BZ7An2IAifYgCIiAIiIAiIgCIiAIiIAiIgCIiAIiIAnxhPsQDUrUrzTxeGkqVnx0BkSVy0ZWKVtTZ9wdJT9q4Nwb69+uvfb8Z12pgwZqvsemd6gzy1qEaqtJHtw+MlRltRZxB6DObZCx7r/8A5JXZ3I/EVTdhkHbq067S2VTHyR7psrhwOEyp4GET1Vema01lZexTNj8i6VOxYZj2/pLRh8Cq7hab4Sess9sacY6Hyp1Zzd5MwrSnsJMlp9tLmZ4Cz0BPtp9gHy0+xEAREQBERAEREAREQBERAEREAREQBERAEREAREQBERAEREAT5ESAIiJIE+iIgCIiAIiIAiIgCIiAIiIAiIgCIiAIiIAiIgCIiAf/2Q==", "Radio", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG_TEXT, new MultiItemSectionModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS78sds-Gau5fd1Xtd_sRmXxeWq0O-dAncNyjfsIJLYgLKcAZbO", "Tourch Light", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Plan about the safe place with family", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        return list;
    }

    public static List<SectionMultipleItem> getThingsToDoWhenHappens() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, true));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        list.add(new SectionMultipleItem(true, "Counts", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Male", "54564564")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Female", "445445")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total population", "564645645")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Houses", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Hospital", "1,23,45,665")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Schools", "1,23,45,665")));



        return list;
    }

    public static List<SectionMultipleItem> getThingsToDoAfter() {
        List<SectionMultipleItem> list = new ArrayList<>();
//        MultiItemSectionModel video = new MultiItemSectionModel(HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK, CYM_CHAD);

        // add section data
        list.add(new SectionMultipleItem(true, "Kathmandu Metropolitian City", false, true));
        // add multiple type item data ---start---
        list.add(new SectionMultipleItem(SectionMultipleItem.IMG, new MultiItemSectionModel("http://3.bp.blogspot.com/-SsnyMNpjZWA/UvpdMrkMb-I/AAAAAAAAAGE/8iuSHzis858/s1600/Landuse+Zoning+Map.jpg","", "")));
        // ---end---

        list.add(new SectionMultipleItem(true, "General Information", false, true));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Location", "Kathmandu District")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Website", "kathmanduMetro.com.np")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Email", "kathmandumetro@gmail.com")));
        list.add(new SectionMultipleItem(SectionMultipleItem.TEXT, new MultiItemSectionModel("", "Total Houses", "1,23,45,665")));

        return list;
    }
}
