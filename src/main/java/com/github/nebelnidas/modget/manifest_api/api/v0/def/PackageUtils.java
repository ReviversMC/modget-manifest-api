package com.github.nebelnidas.modget.manifest_api.api.v0.def;

import java.util.List;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Package;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;

public interface PackageUtils {

	public Package downloadPackage(List<Repository> repos, String publisher, String id);

}
