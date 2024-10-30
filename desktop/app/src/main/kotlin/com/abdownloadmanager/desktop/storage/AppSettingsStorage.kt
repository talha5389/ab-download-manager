package com.abdownloadmanager.desktop.storage

import com.abdownloadmanager.desktop.utils.*
import androidx.datastore.core.DataStore
import arrow.optics.Lens
import arrow.optics.optics
import ir.amirab.util.config.booleanKeyOf
import ir.amirab.util.config.intKeyOf
import ir.amirab.util.config.longKeyOf
import ir.amirab.util.config.stringKeyOf
import ir.amirab.util.config.MapConfig
import kotlinx.serialization.Serializable
import java.io.File

@optics
@Serializable
data class AppSettingsModel(
    val theme: String = "dark",
    val mergeTopBarWithTitleBar: Boolean = false,
    val threadCount: Int = 5,
    val dynamicPartCreation: Boolean = true,
    val useServerLastModifiedTime: Boolean = false,
    val useSparseFileAllocation: Boolean = true,
    val useAverageSpeed: Boolean = true,
    val speedLimit: Long = 0,
    val autoStartOnBoot: Boolean = true,
    val notificationSound: Boolean = true,
    val defaultDownloadFolder: String = File(System.getProperty("user.home"))
        .resolve("Downloads/ABDM")
        .canonicalFile.absolutePath,
    val browserIntegrationEnabled: Boolean = true,
    val browserIntegrationPort: Int = 15151,
) {
    companion object {
        val default: AppSettingsModel get() = AppSettingsModel()
    }

    object ConfigLens : Lens<MapConfig, AppSettingsModel> {
        object Keys {
            val theme = stringKeyOf("theme")
            val mergeTopBarWithTitleBar = booleanKeyOf("mergeTopBarWithTitleBar")
            val threadCount = intKeyOf("threadCount")
            val dynamicPartCreation = booleanKeyOf("dynamicPartCreation")
            val useServerLastModifiedTime = booleanKeyOf("useServerLastModifiedTime")
            val useSparseFileAllocation = booleanKeyOf("useSparseFileAllocation")
            val useAverageSpeed = booleanKeyOf("useAverageSpeed")
            val speedLimit = longKeyOf("speedLimit")
            val autoStartOnBoot = booleanKeyOf("autoStartOnBoot")
            val notificationSound = booleanKeyOf("notificationSound")
            val defaultDownloadFolder = stringKeyOf("defaultDownloadFolder")
            val browserIntegrationEnabled = booleanKeyOf("browserIntegrationEnabled")
            val browserIntegrationPort = intKeyOf("browserIntegrationPort")
        }



        override fun get(source: MapConfig): AppSettingsModel {
            val default by lazy { AppSettingsModel.default }
            return AppSettingsModel(
                theme = source.get(Keys.theme) ?: default.theme,
                mergeTopBarWithTitleBar = source.get(Keys.mergeTopBarWithTitleBar) ?: default.mergeTopBarWithTitleBar,
                threadCount = source.get(Keys.threadCount) ?: default.threadCount,
                dynamicPartCreation = source.get(Keys.dynamicPartCreation) ?: default.dynamicPartCreation,
                useServerLastModifiedTime = source.get(Keys.useServerLastModifiedTime) ?: default.useServerLastModifiedTime,
                useSparseFileAllocation = source.get(Keys.useSparseFileAllocation) ?: default.useSparseFileAllocation,
                useAverageSpeed = source.get(Keys.useAverageSpeed) ?: default.useAverageSpeed,
                speedLimit = source.get(Keys.speedLimit) ?: default.speedLimit,
                autoStartOnBoot = source.get(Keys.autoStartOnBoot) ?: default.autoStartOnBoot,
                notificationSound = source.get(Keys.notificationSound) ?: default.notificationSound,
                defaultDownloadFolder = source.get(Keys.defaultDownloadFolder) ?: default.defaultDownloadFolder,
                browserIntegrationEnabled = source.get(Keys.browserIntegrationEnabled)
                        ?: default.browserIntegrationEnabled,
                browserIntegrationPort = source.get(Keys.browserIntegrationPort) ?: default.browserIntegrationPort,
            )
        }

        override fun set(source: MapConfig, focus: AppSettingsModel): MapConfig {
            return source.apply {
                put(Keys.theme, focus.theme)
                put(Keys.mergeTopBarWithTitleBar, focus.mergeTopBarWithTitleBar)
                put(Keys.threadCount, focus.threadCount)
                put(Keys.dynamicPartCreation, focus.dynamicPartCreation)
                put(Keys.useServerLastModifiedTime, focus.useServerLastModifiedTime)
                put(Keys.useSparseFileAllocation, focus.useSparseFileAllocation)
                put(Keys.useAverageSpeed, focus.useAverageSpeed)
                put(Keys.speedLimit, focus.speedLimit)
                put(Keys.autoStartOnBoot, focus.autoStartOnBoot)
                put(Keys.notificationSound, focus.notificationSound)
                put(Keys.defaultDownloadFolder, focus.defaultDownloadFolder)
                put(Keys.browserIntegrationEnabled, focus.browserIntegrationEnabled)
                put(Keys.browserIntegrationPort, focus.browserIntegrationPort)
            }
        }
    }
}

class AppSettingsStorage(
    settings: DataStore<MapConfig>,
) : ConfigBaseSettingsByMapConfig<AppSettingsModel>(settings, AppSettingsModel.ConfigLens) {
    var theme = from(AppSettingsModel.theme)
    var mergeTopBarWithTitleBar = from(AppSettingsModel.mergeTopBarWithTitleBar)
    val threadCount = from(AppSettingsModel.threadCount)
    val dynamicPartCreation = from(AppSettingsModel.dynamicPartCreation)
    val useServerLastModifiedTime = from(AppSettingsModel.useServerLastModifiedTime)
    val useSparseFileAllocation = from(AppSettingsModel.useSparseFileAllocation)
    val useAverageSpeed = from(AppSettingsModel.useAverageSpeed)
    val speedLimit = from(AppSettingsModel.speedLimit)
    val autoStartOnBoot = from(AppSettingsModel.autoStartOnBoot)
    val notificationSound = from(AppSettingsModel.notificationSound)
    val defaultDownloadFolder = from(AppSettingsModel.defaultDownloadFolder)
    val browserIntegrationEnabled = from(AppSettingsModel.browserIntegrationEnabled)
    val browserIntegrationPort = from(AppSettingsModel.browserIntegrationPort)
}