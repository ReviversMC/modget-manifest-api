package com.github.reviversmc.modget.manifests.spec3.deserialization;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.mod.ModPackageImpl;

public class PackageIdToModPackageConverter extends StdConverter<List<String>, List<ModPackage>> {

    @Override
    public List<ModPackage> convert(List<String> packageIds) {
        List<ModPackage> modPackages = new ArrayList<>(4);

        for (String packageId : packageIds) {
            modPackages.add(new ModPackageImpl(packageId));
        }

        return modPackages;
    }
}
