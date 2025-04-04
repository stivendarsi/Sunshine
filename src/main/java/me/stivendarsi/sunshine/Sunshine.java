package me.stivendarsi.sunshine;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class Sunshine extends JavaPlugin {

    @Override
    public void onEnable() {


    }

    @Override
    public void onDisable() {
        // Plugin shutdosffaffa
    }

    public static TagResolver finalTagResolver(Player player) {
        TagResolver playerBlockPosResolver = TagResolver.builder()
                .tag("player_block_x", Tag.selfClosingInserting(Component.text(player.getLocation().getBlockX())))
                .tag("player_block_y", Tag.selfClosingInserting(Component.text(player.getLocation().getBlockY())))
                .tag("player_block_z", Tag.selfClosingInserting(Component.text(player.getLocation().getBlockZ())))
                .build();

        TagResolver playerFinePosResolver = TagResolver.builder()
                .tag("player_x", Tag.selfClosingInserting(Component.text(player.getX())))
                .tag("player_y", Tag.selfClosingInserting(Component.text(player.getY())))
                .tag("player_z", Tag.selfClosingInserting(Component.text(player.getZ())))
                .build();


        TagResolver tags = TagResolver.builder()
                .resolver(Formatter.date("date", LocalDateTime.now(ZoneId.of("Asia/Jerusalem"))))
                .tag("player_display_name", Tag.selfClosingInserting(player.displayName()))
                .tag("player_name", Tag.selfClosingInserting(Component.text(player.getName())))
                .tag("placeholder_api", (args, context) -> {
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        String placeholder = args.popOr("Placeholder Not Found").value();
                        return Tag.selfClosingInserting(Component.text(PlaceholderAPI.setPlaceholders(player, placeholder)));
                    } else return Tag.selfClosingInserting(Component.empty());
                })
                .build();
        return TagResolver.resolver(tags,playerBlockPosResolver,playerFinePosResolver);
    }
}
