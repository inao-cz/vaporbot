package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.entity.server.Server;

public final class Setup extends Command {
    public Setup(NewMain instance){
        super(instance, "setup", "setup", null);
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        Server server = instance.getApi().getServerById(instance.getId()).get();
        server.createTextChannelBuilder()
                .setName("welcome")
                .setTopic("Welcome room")
                .addPermissionOverwrite(server.getRolesByName("@everyone").get(0), new PermissionsBuilder().setAllowed(PermissionType.SEND_MESSAGES).build())
                .create()
                .join();

    }
}
