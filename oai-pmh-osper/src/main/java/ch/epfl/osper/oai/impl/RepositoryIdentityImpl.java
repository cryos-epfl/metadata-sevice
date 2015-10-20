package ch.epfl.osper.oai.impl;

import ch.epfl.osper.oai.interfaces.RepositoryIdentity;

import javax.inject.Named;

/**
 * Created by kryvych on 20/10/15.
 */
@Named
public class RepositoryIdentityImpl implements RepositoryIdentity {
    @Override
    public String baseURL() {
        return "http://montblanc.slf.ch:8095/oai";
    }

    @Override
    public String repositoryName() {
        return "SLF OAI Repository";
    }

    @Override
    public String adminEmail() {
        return "nkryvych@gmail.com";
    }

    @Override
    public String deletedRecords() {
        return "transient";
    }

    @Override
    public String granularity() {
        return "yyyy-MM-dd'T'HH:mm:ss";
    }
}
