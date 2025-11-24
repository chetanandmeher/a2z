    package com.a2z.model;

    import jakarta.persistence.Entity;
    import lombok.*;

    /**
     * @author Chetanand Meher
     */
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public class BankDetails {

        private String accountHolderName;
        private String accountNumber;
        private String ifscCode;
        private String bankName;
        private String branchName;
    }
