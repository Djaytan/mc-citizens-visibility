package fr.voltariuss.bukkit.citizens_visibility.view;

import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

@Singleton
public class CitizenVisibilityMessage {

  private final MiniMessage miniMessage;
  private final ResourceBundle resourceBundle;

  @Inject
  public CitizenVisibilityMessage(
      @NotNull MiniMessage miniMessage, @NotNull ResourceBundle resourceBundle) {
    this.miniMessage = miniMessage;
    this.resourceBundle = resourceBundle;
  }

  public @NotNull Component visibilityToggled(
      @NotNull String playerName,
      int citizenId,
      @NotNull String citizenName,
      boolean isCitizenVisible) {
    return miniMessage.deserialize(
        resourceBundle.getString("citizen_visibility.message.on_citizen_visibility_toggled"),
        TagResolver.resolver(
            Placeholder.unparsed("cv_citizen_id", Integer.toString(citizenId)),
            Placeholder.unparsed("cv_citizen_name", citizenName),
            Placeholder.component(
                "cv_citizen_visibility", citizenVisibilityState(isCitizenVisible)),
            Placeholder.unparsed("cv_player_name", playerName)));
  }

  public @NotNull Component visibilityToggledForAllPlayers(
      int citizenId, @NotNull String citizenName, boolean isCitizenVisible) {
    return miniMessage.deserialize(
        resourceBundle.getString(
            "citizen_visibility.message.on_citizen_visibility_toggled_for_all_players"),
        TagResolver.resolver(
            Placeholder.unparsed("cv_citizen_id", Integer.toString(citizenId)),
            Placeholder.unparsed("cv_citizen_name", citizenName),
            Placeholder.component(
                "cv_citizen_visibility", citizenVisibilityState(isCitizenVisible))));
  }

  public @NotNull Component visibilityNotChanged(
      @NotNull String playerName,
      int citizenId,
      @NotNull String citizenName,
      boolean isCitizenVisible) {
    return miniMessage.deserialize(
        resourceBundle.getString("citizen_visibility.message.citizen_visibility_not_changed"),
        TagResolver.resolver(
            Placeholder.unparsed("cv_citizen_id", Integer.toString(citizenId)),
            Placeholder.unparsed("cv_citizen_name", citizenName),
            Placeholder.component(
                "cv_citizen_visibility", citizenVisibilityState(isCitizenVisible)),
            Placeholder.unparsed("cv_player_name", playerName)));
  }

  private @NotNull Component citizenVisibilityState(boolean isCitizenVisible) {
    String messageKey = "citizen_visibility.message.citizen_visibility.hidden";

    if (isCitizenVisible) {
      messageKey = "citizen_visibility.message.citizen_visibility.shown";
    }

    return miniMessage.deserialize(resourceBundle.getString(messageKey));
  }

  public @NotNull Component citizenNotFound(int citizenId) {
    return miniMessage.deserialize(
        resourceBundle.getString("citizen_visibility.message.citizen_not_found"),
        TagResolver.resolver(Placeholder.unparsed("cv_citizen_id", Integer.toString(citizenId))));
  }
}
