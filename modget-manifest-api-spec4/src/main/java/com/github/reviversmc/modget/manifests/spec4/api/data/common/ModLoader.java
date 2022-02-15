package com.github.reviversmc.modget.manifests.spec4.api.data.common;

/*
 * An enum of all (working) Minecraft mod loaders (sorted by launch date)
 */
public enum ModLoader {
    /*
     * The first proper Minecraft mod loader to ever exist.
     *
     * Minecraft versions: Beta to 1.6.2
     *
     * See https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1272333-risugamis-mods-updated
     */
    RISUGAMI,

    /*
     * Clean-room rewrite of Risugami's mod loader, became independent later.
     *
     * Minecraft versions: 1.1 to latest
     *
     * See https://github.com/MinecraftForge
     */
    FORGE,

    /*
     * Mod loader with client-side-only API. Can be loaded on top of Forge.
     *
     * Minecraft versions: 1.5.2 to 1.12.2
     *
     * See https://www.liteloader.com/
     */
    LITELOADER,

    /*
     * Mod loader with a powerful, lightweight, and easy to use LiteAPI.
     * BlazeLoader mods using LiteAPI are automatically LiteLoader mods as well!
     *
     * Minecraft versions: 1.6.4 to 1.12.1
     *
     * See here: https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/wip-mods/1445301-blazeloader-a-powerful-lightweight-and-easy-to-use
     */
    BLAZELOADER,

    /*
     * A lightweight, reflection based mod loader which aims to be
     * user friendly and later version independent.
     *
     * Minecraft versions: 1.8 and 1.15
     *
     * See https://github.com/RopeMC
     */
    ROPE,

    /*
     * Mod loader for advanced modders.
     *
     * Minecraft versions: 1.8.3
     *
     * See https://www.cuchazinteractive.com/m3l/
     */
    M3L,

    /*
     * Minecraft TweakClass mod Loader for 1.9/1.10/1.11 snapshot mods.
     *
     * Minecraft versions: 1.9 to 1.11
     *
     * See https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/2488387-meddle-minecraft-tweakclass-mod-loader-1-9-1-10-1
     */
    MEDDLE,

    /*
     * Lightweight mod loader for Minecraft 1.13.
     *
     * Minecraft versions: 1.13 to 1.13.2
     *
     * See https://www.curseforge.com/minecraft/mc-mods/rift
     */
    RIFT,

    /*
     * Lightweight mod loader for Minecraft.
     *
     * Minecraft versions: 1.14 to latest
     *
     * See https://fabricmc.net/
     */
    FABRIC
}
