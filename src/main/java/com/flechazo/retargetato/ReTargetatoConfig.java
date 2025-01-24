package com.flechazo.retargetato;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.List;
@Config(name = "retargetato")
public class ReTargetatoConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public String listType = "B";

    @ConfigEntry.Gui.Tooltip
    public List<String> mobList = Lists.newArrayList();

    @ConfigEntry.Gui.Tooltip()
    public List<String> filteredMobList = Lists.newArrayList("minecraft:piglin", "minecraft:piglin_brute");

    @ConfigEntry.Gui.Tooltip
    public List<String> filteredTargetList = Lists.newArrayList("minecraft:wither_skeleton", "minecraft:wither_skeleton");

    @Override
    public void validatePostLoad() {
        if (listType == null || listType.trim().isEmpty()) {
            listType = "B";
        }
    }
}