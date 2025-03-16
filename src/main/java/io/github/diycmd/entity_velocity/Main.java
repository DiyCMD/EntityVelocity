package io.github.diycmd.entity_velocity;

import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPILogger;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.FloatArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.executors.CommandExecutor;

public class Main extends JavaPlugin {
    @Override
    public void onLoad() {
        CommandAPI.setLogger(CommandAPILogger.fromJavaLogger(getLogger()));
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable();

        // float指定用
        CommandExecutor fcmdExecutor = (sender, args) -> {
            Entity target = (Entity) args.get("target");
            float velocity_x = (float) args.get("velocity_x");
            float velocity_y = (float) args.get("velocity_y");
            float velocity_z = (float) args.get("velocity_z");

            target.setVelocity(new Vector(velocity_x, velocity_y, velocity_z));
        };

        // int指定用 10000倍で設定
        CommandExecutor icmdExecutor = (sender, args) -> {
            Entity target = (Entity) args.get("target");
            float velocity_x = (int) args.get("velocity_x");
            float velocity_y = (int) args.get("velocity_y");
            float velocity_z = (int) args.get("velocity_z");

            target.setVelocity(new Vector(velocity_x / 10000, velocity_y / 10000, velocity_z / 10000));
        };

        CommandTree cmd = new CommandTree("evelocity");
        cmd.withPermission(CommandPermission.OP);
        cmd.then(
            new EntitySelectorArgument.OneEntity("target")
                .thenNested(
                    new LiteralArgument("seti"),
                    new IntegerArgument("velocity_x", -1000000, 1000000),
                    new IntegerArgument("velocity_y", -1000000, 1000000),
                    new IntegerArgument("velocity_z", -1000000, 1000000)
                        .executes(icmdExecutor))
                .thenNested(
                    new LiteralArgument("setf"),
                    new FloatArgument("velocity_x", -100, 100),
                    new FloatArgument("velocity_y", -100, 100),
                    new FloatArgument("velocity_z", -100, 100)
                        .executes(fcmdExecutor)));

        cmd.register();
    }

    @Override
    public void onDisable() {
    }
}