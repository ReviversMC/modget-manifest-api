package com.github.reviversmc.modget.manifests.compat.spec3;

import java.util.ArrayList;

import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable.LookupTableEntryImpl;
import com.github.reviversmc.modget.manifests.spec3.impl.data.mod.ModPackageImpl;
import com.github.reviversmc.modget.manifests.spec3.util.ManifestUtils;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.common.NameUrlPairImpl;

public class Spec3ToSpec4ManifestCompat {
	public com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest v4Manifest;

	public static Spec3ToSpec4ManifestCompat create() {
		return new Spec3ToSpec4ManifestCompat();
	}


	public com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest downloadAndConvertManifest(
		com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry v4Entry,
		com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage v4Package
	) throws Exception
	{
		ModPackage v3Package = new ModPackageImpl(v4Package.getPublisher(), v4Package.getModId());
		LookupTableEntry v3Entry = new LookupTableEntryImpl(null);

		ModManifest v3Manifest = ManifestUtils.create().downloadManifest(v3Entry, v3Package);

		return convertManifest(v3Manifest, v4Package, v4Entry);
	}



	public com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest convertManifest(
		ModManifest v3Manifest,
		com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage v4Package,
		com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry v4Entry
	)
	{
		// Create new v4 manifest
		return new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.ModManifestImpl(v4Package, v4Entry) {{
			// Copy basic metadata
			setManifestSpecVersion(v3Manifest.getManifestSpecVersion());
			setPublisher(v3Manifest.getPublisher());
			setIconUrls(null);
			setStatus(null);
			setUpdatedAlternatives(null);
			setName(v3Manifest.getName());
			setDescription(v3Manifest.getDescription());
			setAuthors(null);
			setHome(v3Manifest.getHome());
			setSource(v3Manifest.getSource());
			setIssues(v3Manifest.getIssues());
			setSupport(v3Manifest.getSupport());
			setWiki(null);
			setChats(null);

			// Copy mod versions
			setVersions(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion>() {{
				for (ModVersion v3Version : v3Manifest.getDownloads()) {
					add(convertModVersion(v3Version));
				}
			}});
		}};
	}



	public com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion convertModVersion(
		ModVersion v3Version
	)
	{
		return new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModVersionImpl(v4Manifest) {{
			ModManifest v3Manifest = v3Version.getParentManifest();

			// Copy version metadata
			setVersion(v3Version.getVersion());
			setLoaders(v3Manifest.getLoader());
			setMinecraftVersions(v3Version.getMinecraftVersions());
			setChannel(null);
			setDepends(null);
			setBundles(null);
			setBreaks(null);
			setConflicts(null);
			setRecommends(null);
			setLicense(v3Manifest.getLicense());
			setFileType(v3Manifest.getModType());
			setMd5(v3Version.getMd5());

			// Copy environment
			setEnvironment(new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModEnvironmentImpl(this) {{
				switch (v3Manifest.getSide()) {
					case "client":
						setClient("required");
						break;
					case "server":
						setServer("required");
						break;
					case "both":
						setClient("required");
						setServer("required");
						break;
				}
			}});
	
			// Copy third party ids
			setThirdPartyIds(new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModThirdPartyIdsImpl(this) {{
				setCurseforge(v3Manifest.getThirdPartyIds().getCurseforge());
				setModrinth(v3Manifest.getThirdPartyIds().getModrinth());
			}});
	
			// Copy version download page urls
			setDownloadPageUrls(new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModDownloadsImpl(this) {{
				for (ModDownload v3Download : v3Version.getDownloadPageUrls()) {
					String url = v3Download.getUrl();
					switch (v3Download.getName()) {
						case "Modrinth":
							setModrinth(url);
							break;
						case "CurseForge":
							setCurseforge(url);
							break;
						case "GitHub":
						case "GitLab":
						case "Gitea":
							setSourceControl(url);
							break;
						default:
							addOther(new NameUrlPairImpl(v3Download.getName(), url));
							break;
					}
				}
			}});
	
			// Copy version file urls
			setFileUrls(new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModDownloadsImpl(this) {{
				for (ModDownload v3Download : v3Version.getDownloadPageUrls()) {
					String url = v3Download.getUrl();
					switch (v3Download.getName()) {
						case "Modrinth":
							setModrinth(url);
							break;
						case "CurseForge":
							setCurseforge(url);
							break;
						case "GitHub":
						case "GitLab":
						case "Gitea":
							setSourceControl(url);
							break;
						default:
							addOther(new NameUrlPairImpl(v3Download.getName(), url));
							break;
					}
				}
			}});
		}};
	}

}
