package com.app.demoprojection.projections;

public interface PersonProjection {
    String getFirstName();
    String getLastName();
    String getProfession();
    String getEmail();
    AddressProjection getAddresse();
    interface AddressProjection {
        String getVille();
        String getQuartier();
    }
}
