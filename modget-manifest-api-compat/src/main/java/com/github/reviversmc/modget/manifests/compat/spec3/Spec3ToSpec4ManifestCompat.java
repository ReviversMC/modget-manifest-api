package com.github.reviversmc.modget.manifests.compat.spec3;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.reviversmc.modget.manifests.spec3.api.data.ManifestRepository;
import com.github.reviversmc.modget.manifests.spec3.api.data.lookuptable.LookupTableEntry;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.main.ModManifest;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModDownload;
import com.github.reviversmc.modget.manifests.spec3.api.data.manifest.version.ModVersion;
import com.github.reviversmc.modget.manifests.spec3.api.data.mod.ModPackage;
import com.github.reviversmc.modget.manifests.spec3.impl.data.ManifestRepositoryImpl;
import com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable.LookupTableEntryImpl;
import com.github.reviversmc.modget.manifests.spec3.impl.data.lookuptable.LookupTableImpl;
import com.github.reviversmc.modget.manifests.spec3.impl.data.mod.ModPackageImpl;
import com.github.reviversmc.modget.manifests.spec3.util.ModManifestUtils;
import com.github.reviversmc.modget.manifests.spec3.util.ManifestRepositoryUtils;
import com.github.reviversmc.modget.manifests.spec4.api.data.manifest.common.NameUrlPair;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.common.NameUrlPairImpl;
import com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModVersionVariantImpl;

public class Spec3ToSpec4ManifestCompat {
	com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest v4Manifest
		= new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.ModManifestImpl(null, null);
	ModPackage v3Package = null;

	public static Spec3ToSpec4ManifestCompat create() {
		return new Spec3ToSpec4ManifestCompat();
	}


	public com.github.reviversmc.modget.manifests.spec4.api.data.manifest.main.ModManifest downloadAndConvertManifest(
		com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry v4Entry,
		com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage v4Package
	) throws Exception
	{
		// Package
		v3Package = new ModPackageImpl(v4Package.getPackageId());

		// Repository
		com.github.reviversmc.modget.manifests.spec4.api.data.ManifestRepository v4Repo
			= v4Entry.getParentLookupTable().getParentRepository();
		ManifestRepository v3Repo = new ManifestRepositoryImpl(v4Repo.getId(), v4Repo.getUri()) {{
			setSupportedManifestSpecMajorVersions(ManifestRepositoryUtils.create().getAvailableManifestSpecMajorVersions(this));
		}};

		// Lookup table entry
		LookupTableEntry v3Entry = new LookupTableEntryImpl(new LookupTableImpl(v3Repo));

		// Manifest
		ModManifest v3Manifest = ModManifestUtils.create().downloadManifest(v3Entry, v3Package);
		convertManifest(v3Manifest, v4Package, v4Entry);
		v4Manifest.setParentPackage(v4Package);
		v4Manifest.setParentLookupTableEntry(v4Entry);

		return v4Manifest;
	}



	public void convertManifest(
		ModManifest v3Manifest,
		com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage v4Package,
		com.github.reviversmc.modget.manifests.spec4.api.data.lookuptable.LookupTableEntry v4Entry
	)
	{
		// Copy basic metadata
		v4Manifest.setManifestSpecVersion(v3Manifest.getManifestSpecVersion());
		v4Manifest.setPublisher(v3Manifest.getPublisher());
		v4Manifest.setIconUrls(null);
		v4Manifest.setStatus(null);
		v4Manifest.setUpdatedAlternatives(null);
		v4Manifest.setName(v3Manifest.getName());
		v4Manifest.setDescription(v3Manifest.getDescription());
		v4Manifest.setAuthors(null);
		v4Manifest.setHome(v3Manifest.getHome());
		v4Manifest.setSource(v3Manifest.getSource());
		v4Manifest.setIssues(v3Manifest.getIssues());
		v4Manifest.setSupport(v3Manifest.getSupport());
		v4Manifest.setWiki(null);

		// Copy chats
		if (v3Manifest.getChat() != null) {
			v4Manifest.setChats(new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.main.ModChatsImpl(v4Manifest) {{
				if (v3Manifest.getChat().toLowerCase().contains("discord")) {
					setDiscord(v3Manifest.getChat());
				} else if (v3Manifest.getChat().toLowerCase().contains("irc")) {
					setIrc(v3Manifest.getChat());
				} else {
					setOthers(new ArrayList<NameUrlPair>() {{
						add(new NameUrlPairImpl(null, v3Manifest.getChat()));
					}});
				}
			}});
		}

		// Copy mod versions
		v4Manifest.setVersions(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion>() {{
			for (ModVersion v3Version : v3Manifest.getDownloads()) {
				com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion v4Version
					= convertModVersion(v3Version);
				v4Version.setParentManifest(v4Manifest);
				add(v4Version);
			}
		}});
	}



	public <ModVersionVariant> com.github.reviversmc.modget.manifests.spec4.api.data.manifest.version.ModVersion convertModVersion(
		ModVersion v3Version
	)
	{
		return new com.github.reviversmc.modget.manifests.spec4.impl.data.manifest.version.ModVersionImpl(v4Manifest) {{
			ModManifest v3Manifest = v3Version.getParentManifest();

			// Copy version metadata
			setVersion(v3Version.getVersion());

			// Copy version variant
			ModVersionVariantImpl variant = new ModVersionVariantImpl(this) {{
				setLoaders(v3Version.getLoaders());
				setMinecraftVersions(v3Version.getMinecraftVersions());
				setChannel(null);
				setBundles(null);
				setConflicts(null);
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

				setDepends(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage>() {{
					for (ModPackage v3DependentPackage : v3Version.getDepends()) {
						add(new com.github.reviversmc.modget.manifests.spec4.impl.data.mod.ModPackageImpl(v3DependentPackage.getPackageId()) {{
							setVersion(v3DependentPackage.getVersion());
						}});
					}
				}});
				setBreaks(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage>() {{
					for (ModPackage v3BreakingPackage : v3Version.getBreaks()) {
						add(new com.github.reviversmc.modget.manifests.spec4.impl.data.mod.ModPackageImpl(v3BreakingPackage.getPackageId()) {{
							setVersion(v3BreakingPackage.getVersion());
						}});
					}
				}});
				setRecommends(new ArrayList<com.github.reviversmc.modget.manifests.spec4.api.data.mod.ModPackage>() {{
					for (ModPackage v3RecommendedPackage : v3Version.getRecommends()) {
						add(new com.github.reviversmc.modget.manifests.spec4.impl.data.mod.ModPackageImpl(v3RecommendedPackage.getPackageId()) {{
							setVersion(v3RecommendedPackage.getVersion());
						}});
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
						switch (v3Download.getName().toLowerCase()) {
							case "modrinth":
								setModrinth(url);
								break;
							case "curseforge":
								setCurseforge(url);
								break;
							case "github":
							case "gitlab":
							case "gitea":
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
					for (ModDownload v3Download : v3Version.getFileUrls()) {
						String url = v3Download.getUrl();
						switch (v3Download.getName().toLowerCase()) {
							case "modrinth":
								setModrinth(url);
								break;
							case "curseforge":
								setCurseforge(url);
								break;
							case "github":
							case "gitlab":
							case "gitea":
								setSourceControl(url);
								break;
							default:
								addOther(new NameUrlPairImpl(v3Download.getName(), url));
								break;
						}
					}
				}});
			}};
			setVariants(Arrays.asList(variant));
		}};
	}

}
