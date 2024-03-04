package dev.flrp.espresso.message;

import dev.flrp.espresso.message.settings.HologramSetting;
import dev.flrp.espresso.message.settings.TitleSetting;
import dev.flrp.espresso.util.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Message {

    private List<String> template = new ArrayList<>();
    private MessageType type;

    private HologramSetting hologramSetting;
    private TitleSetting titleSetting;

    private Message(String line) {
        template.add(line);
    }

    private Message(List<String> lines) {
        this.template = lines;
    }

    public static Message of(String line) {
        return new Message(line);
    }

    public static Message of(List<String> lines) {
        return new Message(lines);
    }

    public Message register(String placeholder, String value) {
        template.replaceAll(string -> string.replace(placeholder, value));
        return this;
    }

    public Message as(MessageType type) {
        this.type = type;
        return this;
    }

    public Message with(HologramSetting hologramSetting) {
        this.hologramSetting = hologramSetting;
        return this;
    }

    public Message with(TitleSetting titleSetting) {
        this.titleSetting = titleSetting;
        return this;
    }

    public void to(Player player) {
        validateSettings();
        template = StringUtils.parseColor(template);
        switch (type) {
            case CHAT:
                player.sendMessage(String.join("\n", template));
                break;
            case ACTION_BAR:
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.join("\n", template)));
                break;
            case HOLOGRAM:
                hologramSetting.getHologramProvider().createHologram(UUID.randomUUID().toString(), player.getLocation(), template);
                if(hologramSetting.getDuration() > 0)
                    Bukkit.getScheduler().runTaskLater(hologramSetting.getPlugin(), () -> hologramSetting.getHologramProvider().removeHologram(player.getUniqueId().toString()), hologramSetting.getDuration());
                break;
            case TITLE:
                if(titleSetting.isSubTitle()) {
                    titleSetting.setSubTitle(template.get(0));
                } else {
                    titleSetting.setTitle(template.get(0));
                }
                player.sendTitle(titleSetting.getTitle(), titleSetting.getSubTitle(), titleSetting.getFadeIn(), titleSetting.getStay(), titleSetting.getFadeOut());
                break;
        }
    }

    public void at(LivingEntity entity) {
        if (type == MessageType.HOLOGRAM) {
            hologramSetting.getHologramProvider().createHologram(UUID.randomUUID().toString(), entity.getLocation(), template);
            if (hologramSetting.getDuration() > 0) {
                Bukkit.getScheduler().runTaskLater(hologramSetting.getPlugin(), () -> hologramSetting.getHologramProvider().removeHologram(entity.getUniqueId().toString()), hologramSetting.getDuration());
            }
        } else {
            throw new UnsupportedOperationException("This method is only supported for holograms. Use to(Player player) instead.");
        }
    }

    private void validateSettings() {
        if ((type == MessageType.HOLOGRAM && hologramSetting == null) || (type == MessageType.TITLE && titleSetting == null)) {
            throw new NullPointerException(type == MessageType.HOLOGRAM ? "HologramSetting is null" : "TitleSetting is null");
        }
    }

}
