package com.app.demoprojection.projections;

public interface PersonProjection2 {
    String getFirstName();
    String getEmail();
    AddressProjection getAddresse();
    interface AddressProjection {
        String getVille();
    }
}
