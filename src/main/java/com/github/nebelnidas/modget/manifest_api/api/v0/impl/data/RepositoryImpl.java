package com.github.nebelnidas.modget.manifest_api.api.v0.impl.data;

import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.nebelnidas.modget.manifest_api.ManifestApi;
import com.github.nebelnidas.modget.manifest_api.api.v0.config.ApiV0Config;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.Repository;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTable;
import com.github.nebelnidas.modget.manifest_api.api.v0.def.data.lookuptable.LookupTableEntry;
import com.github.nebelnidas.modget.manifest_api.api.v0.impl.data.lookuptable.LookupTableImpl;

public class RepositoryImpl implements Repository {
	private final int id;
	private final String uri;
	private String uriWithSpec;
	private LookupTable lookupTable;
	private boolean enabled = true;

	public RepositoryImpl(int id, String uri) {
		this.id = id;
		if (uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		this.uri = uri;
		this.uriWithSpec = uri + "/v" + ApiV0Config.MAX_SUPPORTED_MANIFEST_SPEC;
		try {
			refresh();
		} catch (Exception e) {}
	}


	@Override
	public void refresh() throws Exception {
		downloadLookupTable();
	}

	private void downloadLookupTable() throws Exception {
		final LookupTable newLookupTable = new LookupTableImpl(this);

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(LookupTable.class, newLookupTable);
        mapper.setInjectableValues(injectableValues);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		try {
			List<LookupTableEntry> entries = Arrays.asList(mapper.readValue(new URL(String.format("%s/%s", uriWithSpec, "/lookup-table.yaml")), LookupTableEntry[].class));

			newLookupTable.setLookupTableEntries(entries);
			this.lookupTable = newLookupTable;
        } catch (Exception e) {
			if (e instanceof UnknownHostException) {
				ManifestApi.logWarn("Couldn't connect to the manifest repository. Please check your Internet connection!", e.getMessage());
			} else {
				ManifestApi.logWarn("Couldn't connect to the manifest repository", e.getMessage());
			}
			throw e;
        }
	}


	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getUri() {
		return this.uri;
	}

	@Override
	public String getUriWithSpec() {
		return this.uriWithSpec;
	}

	@Override
	public LookupTable getLookupTable() {
		return this.lookupTable;
	}

	@Override
	public void setLookupTable(LookupTable lookupTable) {
		this.lookupTable = lookupTable;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
