package com.a2z.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.*;
import java.util.List;

/**
 * @author Chetanand Meher
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Home {

    private List<HomeCategory> grid;
    private List<HomeCategory> shopByCategory;
    private List<HomeCategory> electricCategory;
    private List<HomeCategory> dealsCategory;
    private List<Deal> deals
            ;


}
