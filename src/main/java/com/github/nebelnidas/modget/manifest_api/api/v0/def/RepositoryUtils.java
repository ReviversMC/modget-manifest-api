package com.github.nebelnidas.modget.manifest_api.api.v0.def;

import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;

public interface RepositoryUtils {

	public boolean checkForNewVersion(Repository repo) throws Exception;

}
