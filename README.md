<div align="center">
<h2>Discord Bot project.</h2>
</div>

<div align="center">
created by inao, LiquidDev and vapor.
</div>

<h3>
What is this project?
</h3>


This project is just a simple Discord bot, coded in Java. 
Why am I sharing it? 
Well, I'm sharing it because I think this is bot that can be useful to someone (googled or someone from Nulled ;) ).
Can you use this bot? Absolutely! That's why it's config.yml there! **Just please, keep me and my fellows in command 'About' since we have a lot of time on this bot. Thank you :) (btw, it's even in the license)**.


<br>

<h3>
How to setup this bot
</h3>


1. Setup example-config.yml. If you aren't going to use some commands, you can easily turn them off *KEEP DATATYPES AS SEEN IN EXAMPLE-CONFIG!*
    1. Be sure to setup apis -> "discordProductionApi" (Use your Bot secret key)
    2. Be sure you have all commands and features setup correctly
2. After setting up, rename example-config.yml to config.yml.
3. Keep config.yml and .jar in the same directory!
4. Setup Discord server.
    1. Create room `welcome`. Bot will post welcome messages here
    2. Create room `bans`. Bot will announce any banned users there.
    3. Create room `update`. Bot will announce publicly new updates.
    4. Create room `bot-commands`. Normal users will be able to use commands only there.
    5. If you are going to use manual approve system, create category `captcha` and room `waiting-room`. Also create permission groups `Captcha` and `not-approved`
    6. If you are going to use mute system in bot, create room `public-mute`
    7. If you are going to use ticket system in this bot, create category `tickets` and in there room `log`. Bot will use category to create tickets and room log to announce closed tickets
    8. If you are going to use YouTube approve system, create channel `yt-verify` and permission group `YouTube`
    9. If you are going to use Antipihoda, create permission group `anti-pihoda`
    10. If you are going to use Channel logging, create channel `admin-log`<br><br>
    **REMEMBER Bot should have permissions to USE THESE CHANNELS, CATEGORIES AND PERMISSION GROUPS** 
5. Now, you can launch with command `java -jar <bot>.jar`

<p class='red'><strong>DANGER:</strong> Do not ever touch settings/options etc.. where you are not sure. For example: production option is ONLY for development and testing purposes.</p>


<h3>
Commands and settings
</h3>

|  Command | Parameters |  Setting |  Options |  APIs | Needed Perms | Messages |
|---|---|---|---|---|---|---|
| About | None | None  | None  | None  | None  | None |
| Antipihoda | None | Antipihoda | antiGroup | None | None | AntipihodaInGroup, AntipihodaJoined |
| Approve | Client ID **REQUIRED** | Approve | None | None | Manage Roles (Server) | GenericNoPerms, ApproveAlready |
| Count | Finish number **REQUIRED**<br> Preventious number **Not required** | Count | None | None | Create Channels (Server) | GenericNoPerms, GenericFail, GenericArgsErr, CountgameCreated, CountgameClosed, CountgameCompleted |
| Delete | Number of messages **REQUIRED** | Delete | None | None | Kick Users (From Server) | None |
| GenKey | None | ExternalServer | None | None | Server Admin | KeyGenerated |
| Help | None | None | None | None | None | None |
| Mute | User ID **REQUIRED** | Mute | None | None | Mute Users (Server) | GenericNoPerms, GenericArgsErr, MuteSuccess |
| Nicksgen | Length **REQUIRED**<br>Nick **REQUIRED**<br>Count **REQUIRED** | Nicksgen | None | None | None | GenericArgsErr, NicksgenErrNums, NicksgenLimit, NicksgenMinus, NicksGenSuccess |
| Ping | None | production | None | None | None | None |
| Reload | None | Reload | None | None | Server Admin | Included only in class |
| Skid | None | Skid | None | SkidServerUrl | None | Included only in class |
| Thanos | None | Thanos | thanosGroupBlacklist | None | None | Included only in class |
| Ticket | Create/Close **REQUIRED (More info at description)** | Ticket | None | None | Create -> None, Close -> Kick Members (from server) | GenericNoPerms, GenericArgsErr, others included in class |
| Unmute | User ID **REQUIRED** | Mute | None | None | Mute Members (Server) | GenericNoPerms, GenericArgsErr, UnmuteNoMutes, UnmuteSuccess, UnmuteNotMuted |
| Youtube | Start/Verify **REQUIRED (More info at description)** | Youtube | None | YouTubeDataApi | None | GenericArgsErr, YoutubeAlreadyLinked, YoutubeNotValidId, YoutubeAlreadyLinking, YoutubePM, YoutubeSuccess, YoutubeProcessNotStarted 


<h3>
Commands description
</h3>

Parameters are written as a sub-list

1. **About** - Command that contains copyright info _cannot be disabled_
2. **Antipihoda** - Group switch by command
3. **Approve** - Manual approve on User ID (Only if manual approve system is active)<br>
    1. User ID - Parameter, *REQUIRED*, Long. Says what user should be approved. Can look like this: 123456789012345678 (18 chars long)
4. **Count** - CountGame.<br>
    1. Finish number - Parameter *REQUIRED*, Long. Tells finish number of the game. Maximum amount: 9223372036854775807
    2. Last game number - Parameter *Not required*, Long. Start counting from the last game number.
5. **Delete** - Delete messages <br>
    1. Number of messages - Parameter *REQUIRED*, Integer. Tells how many of messages should be deleted. Can be set to all for deleting all messages (I cannot tell if works or not).
6. **GenKey** - Generate Remote Server access keys
7. **Help** - Show help (modified by currently allowed commands)
8. **Mute** - Mute members of the server.
    1. User ID - Parameter, *REQUIRED*, Long. Tells what user should be muted (automatically delete his messages). Can look like this: 123456789012345678 (18 chars long)
9. **Nicksgen** - Create passwords or Minecraft warez nicknames
    1. Length - Parameter, *REQUIRED*, Integer. Sets up length of every single generated password/nickname.
    2. Nick - Parameter, *REQUIRED*, String. Prefix of the password/nickname.
    3. Count - Parameter, *REQUIRED* Integer. How much of these should be generated.
10. **Ping** - Command for testing purposes **ONLY!**
11. **Reload** - Allows you to reload config.yml in a running instance of bot.
12. **Skid** - Command that is proprietary, can be used for your own purposes or deleted.
13. **Thanos** - Generate 50% users of non-blacklisted groups.
14. **Ticket** - Ticket system. Sets ticket reason as a Channel topic.
    1. Create - Parameter, *REQUIRED*, String. Tells command it should work in `create` mode. **USE ONLY WITHOUT CLOSE PARAMETER**
        1. Reason - Parameter, *REQUIRED*, String. Used only when creating. Rough description of the problem.
    2. Close - Parameter, *REQUIRED*, String. Tells command it should `close solved ticket`. **USE ONLY WIHOUT CREATE PARAMETER**
        1. Ticket ID - Parameter, *REQUIRED*, String. ID of a ticket that should be closed and announced as solved.
15. **Unmute** - Revert `Mute` command effects. Messages deleted in muted mode cannot be recovered.
    1. User ID - Parameter, *REQUIRED*, Long. Tells what user should be unmuted. Can look like this: 123456789012345678 (18 chars long)
16. **YouTube** - Automatically check for YouTube subscribers and give users YouTube role if they have enough subscribers.
    1. Start - Parameter, *REQUIRED*, String. Start the verification process. **USE ONLY WITHOUT VERIFY PARAMETER**
        1. Channel ID - Parameter, *REQUIRED*, String. YouTube channel to check.
    2. Verify - Parameter, *REQUIRED*, String. Verify code of the YouTube channel and if the code has been provided as it should be **USE ONLY WITHOUT CREATE PARAMETER**