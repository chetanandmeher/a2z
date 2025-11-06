package com.a2z.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BussinessDetails {
    private String businessName;
    private String businessAddress;
    private String businessMobile;
    private String businessEmail;
    private String logo;
    private String banner;

}
