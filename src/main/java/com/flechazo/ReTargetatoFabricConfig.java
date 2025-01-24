package com.flechazo;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.List;

/**
 * ReTargetato模组配置类
 * 用于管理模组的配置项
 *
 * @author YourName
 */
@Config(name = "retargetato")
public class ReTargetatoFabricConfig implements ConfigData {

    /**
     * 使用 'B' 表示黑名单，其他任何字符串表示白名单。
     */
    @ConfigEntry.Gui.Tooltip
    public String listType = "B";

    /**
     * 需要过滤的实体列表。
     */
    @ConfigEntry.Gui.Tooltip
    public List<String> mobList = Lists.newArrayList();

    /**
     * 高级过滤：需要过滤的实体列表。
     */
    @ConfigEntry.Gui.Tooltip()
    public List<String> filteredMobList = Lists.newArrayList("minecraft:piglin", "minecraft:piglin_brute");

    /**
     * 高级过滤：需要过滤的目标实体列表。
     */
    @ConfigEntry.Gui.Tooltip
    public List<String> filteredTargetList = Lists.newArrayList("minecraft:wither_skeleton", "minecraft:wither_skeleton");

    /**
     * 验证配置值是否合法。
     */
    @Override
    public void validatePostLoad() {
        // 确保 listType 不为空字符串
        if (listType == null || listType.trim().isEmpty()) {
            listType = "B"; // 默认为黑名单模式
        }
    }
}