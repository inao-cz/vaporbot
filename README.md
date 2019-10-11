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

<h2>
How to setup this bot
</h2>


1. Setup example-config.yml. If you aren't going to use some commands, you can easily turn them off *KEEP DATATYPES AS SEEN IN EXAMPLE-CONFIG!*
    1. Be sure to setup apis -> "discordProductionApi" (Use your Bot secret key)
    2. Be sure you have all commands and features setup correctly
    3. For setting up, it's recommended to look at sections `Commands and description` and `Config description`
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


<h2>
Commands and settings
</h2>

| Command    | Parameters                                                         | Setting          | Options                | APIs             | Needed Perms                                        | Messages                                                                                                                            |
|:-----------|:-------------------------------------------------------------------|:-----------------|:-----------------------|:-----------------|:----------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------|
| About      | None                                                               | `None`           | `None`                 | `None`           | None                                                | None                                                                                                                                |
| Antipihoda | None                                                               | `Antipihoda`     | `antiGroup`            | `None`           | None                                                | AntipihodaInGroup, AntipihodaJoined                                                                                                 |
| Approve    | Client ID **REQUIRED**                                             | `Approve`        | `None`                 | `None`           | Manage Roles (Server)                               | GenericNoPerms, ApproveAlready                                                                                                      |
| Count      | Finish number **REQUIRED**<br> Preventious number **Not required** | `Count`          | `None`                 | `None`           | Create Channels (Server)                            | GenericNoPerms, GenericFail, GenericArgsErr, CountgameCreated, CountgameClosed, CountgameCompleted                                  |
| Delete     | Number of messages **REQUIRED**                                    | `Delete`         | `None`                 | `None`           | Kick Users (From Server)                            | None                                                                                                                                |
| GenKey     | None                                                               | `ExternalServer` | `None`                 | `None`           | Server Admin                                        | KeyGenerated                                                                                                                        |
| Help       | None                                                               | `None`           | `None`                 | `None`           | None                                                | None                                                                                                                                |
| Mute       | User ID **REQUIRED**                                               | `Mute`           | `None`                 | `None`           | Mute Users (Server)                                 | GenericNoPerms, GenericArgsErr, MuteSuccess                                                                                         |
| Nicksgen   | Length **REQUIRED**<br>Nick **REQUIRED**<br>Count **REQUIRED**     | `Nicksgen`       | `None`                 | `None`           | None                                                | GenericArgsErr, NicksgenErrNums, NicksgenLimit, NicksgenMinus, NicksGenSuccess                                                      |
| Ping       | None                                                               | `production`     | `None`                 | `None`           | None                                                | None                                                                                                                                |
| Reload     | None                                                               | `Reload`         | `None`                 | `None`           | Server Admin                                        | Included only in class                                                                                                              |
| Skid       | None                                                               | `Skid`           | `None`                 | `SkidServerUrl`  | None                                                | Included only in class                                                                                                              |
| Thanos     | None                                                               | `Thanos`         | `thanosGroupBlacklist` | `None`           | None                                                | Included only in class                                                                                                              |
| Ticket     | Create/Close **REQUIRED (More info at description)**               | `Ticket`         | `None`                 | `None`           | Create -> None, Close -> Kick Members (from server) | GenericNoPerms, GenericArgsErr, others included in class                                                                            |
| Unmute     | User ID **REQUIRED**                                               | `Mute`           | `None`                 | `None`           | Mute Members (Server)                               | GenericNoPerms, GenericArgsErr, UnmuteNoMutes, UnmuteSuccess, UnmuteNotMuted                                                        |
| Youtube    | Start/Verify **REQUIRED (More info at description)**               | `Youtube`        | `None`                 | `YouTubeDataApi` | None                                                | GenericArgsErr, YoutubeAlreadyLinked, YoutubeNotValidId, YoutubeAlreadyLinking, YoutubePM, YoutubeSuccess, YoutubeProcessNotStarted |

<h2>
Commands description
</h2>

Parameters are written as a sub-list

1. **About** - Command that contains copyright info _cannot be disabled_
2. **Antipihoda** - Switch to the preset group by command
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
    
<h2>
Config description
</h2>

<h5>
Section: Messages (Datatype: String [= text])
</h5>

| Message                  | Default placeholders                  | Config Setting | Meaning                                                                                 |
|:-------------------------|:--------------------------------------|:---------------|:----------------------------------------------------------------------------------------|
| GenericFail              | `%command%`                           | More than one  | Generic message for command fail                                                        |
| GenericSuccess           | `%command%`                           | More than one  | Generic message for Successfully executed command                                       |
| GenericNoPerms           | `%command%`                           | More than one  | Generic message for user who tired to use admin command                                 |
| GenericArgsErr           | %command%`                            | More than one  | Generic message for wrong arguments provided                                            |
| AntipihodaInGroup        | `None`                                | Antipihoda     | If user tried to switch group but he is already in                                      |
| AntipihodaJoined         | `None`                                | Antipihoda     | When user successfully changes group, message is sent                                   |
| ApproveAlready           | `None`                                | Approve        | Message sent when already approved user is tried to approve again                       |
| BannedSuccess            | `%user%`<br>`%id%`                    | Bans           | Announced message when user is banned from server.                                      |
| CountgameComplete        | `%user%`                              | Count          | Sent if Count-Game end has been reached                                                 |
| CountgameCreate          | `%number%`                            | Count          | Message sent when new Count-Game has been created                                       |
| CountgameClosed          | `%user%`                              | Count          | If Count-Game is closed by an admin, send this message                                  |
| CaptchaWelcome           | `None`                                | Captcha        | Captcha message, sent to new users.                                                     |
| MuteSuccess              | `%user%`<br>`%id%`                    | Mute           | Announced message when user has been muted.                                             |
| NicksgenErrNums          | `%command%`                           | Nicksgen       | Nicksgen numbers error (Only when bad datatypes or negative numers are provided)        |
| NicksgenLimit            | `None`                                | Nicksgen       | Nicksgen error if user is trying to generate more than 50 nicknames                     |
| NicksgenMinus            | `None`                                | Nicksgen       | Nicksgen error if user has provided negative amount of the nicknames                    |
| NicksgenSuccess          | `None`                                | Nicksgen       | If generation has been successfull and sent to the PM                                   |
| UnmuteNoMutes            | `None`                                | Unmute         | Sent to the channel if admin is trying to unmute even if mute list is empty             |
| UnmuteSuccess            | `%user%`<br>`%id%`                    | Unmute         | Successfully unmuted user.                                                              |
| UnmuteNotMuted           | `None`                                | Unmute         | Thrown when admin tried to unmute non-muted user                                        |
| YoutubeAlreadyLinked     | `None`                                | Youtube        | if YouTube linked user is trying to link again                                          |
| YoutubeNotValidId        | `None`                                | Youtube        | When user tried to start verification process but with wrong ID                         |
| YoutubeAlreadyLinking    | `None`                                | Youtube        | If user is already in linking process but tired to start new                            |
| YoutubePM                | `None`                                | Youtube        | User has successfully started verification process and secret code has been sent to him |
| YoutubeSuccess           | `%ytchannel%`<br>`%user%`<br>`%subs%` | Youtube        | Message that is announced when user has successfully completed verification             |
| YoutubeInvalid           | `None`                                | Youtube        | Message saying that API might be down, Key can be invalid etc..                         |
| YoutubeProcessNotStarted | `None`                                | Youtube        | If user is trying to verify without started process                                     |

<h5>
Section: Settings (Datatype: Boolean [= only true/false])
</h5>

| Setting        | Default value | Toggling             | Meaning                                                                 |
|:---------------|:--------------|:---------------------|:------------------------------------------------------------------------|
| production     | `true`        | Environment          | Setting up Server or development **DO NOT USE IF YOU AREN'T DEVELOPER** |
| Antipihoda     | `false`       | Command              | Toggles command `Antipihoda`                                            |
| Approve        | `false`       | Command, Environment | Enables manual verification of each new user                            |
| Bans           | `true`        | Environment          | Enable ban announcing to the `public-bans` room                         |
| Captcha        | `false`       | Environment          | Turns on simple AntiBot protection                                      |
| Count          | `false`       | Command, Environment | Enables `Count-Game`                                                    |
| ConsoleLog     | `true`        | System               | This will enable logging of commands into the Console                   |
| Delete         | `true`        | Command              | Enable command `Delete`                                                 |
| ExternalServer | `false`       | Environment          | Enable external integration trough packets and AES                      |
| Gitlab         | `true`        | Environment          | AutoUpdate Checking                                                     |
| Mute           | `false`       | Command              | Enable commands `Mute` and `Unmute`                                     |
| MessageLog     | `true`        | Environment          | Logging into channel `admin-log`                                        |
| Nicksgen       | `false`       | Command              | Enable `Nicksgen` command                                               |
| Skid           | `false`       | Command              | Enable `Skid` command **DO NOT USE**                                    |
| Ticket         | `false`       | Command, Environment | Enable ticket system and command `Ticket`                               |
| Thanos         | `false`       | Command              | Enable command `Thanos`                                                 |
| Youtube        | `false`       | Command, API         | Enable YouTube Data V3 integration and command `Youtube`                |

<h5>
Section: Options (Datatype: String [= text])
</h5>

| Option               | Default Value | Connected with        | Meaning                                                                |
|:---------------------|:--------------|:----------------------|:-----------------------------------------------------------------------|
| prefix               | `"!"`         | Environment, Commands | Bot prefix that will be used                                           |
| thanosGroupBlacklist | `""`          | Command               | List of groups that won't be used in command `Thanos`. Splitted by `;` |
| aesKey               | `"null"`      | Environment           | AES 256bit key encoded in Base64 for server communication encryption   |
| aesIv                | `"null"`      | Environment           | AES Initialisation Vector (Base64 encoded)                             |
| externalPort         | `"1337"`      | Environment           | Port for External Server                                               |
| allowedKeys          | `""`          | Environment           | List of allowed keys for the integration. Splitted by `,`              |

<h5>
Section: APIs (Datatype: String [= text])
</h5>

| API | Connected with | Meaning |
|-----|----------------|---------|
|discordProductionApi | Discord API | Main bot API (secret) key. **BOT WON'T WORK WITHOUT THIS!** |
|discordTestingApi | Discord API | Testing bot API (secret) key. **DO NOT USE UNLESS YOU ARE PLANNING TO SWITCH `production` TO FALSE** |
|YouTubeDataApi | Youtube Command | YouTube Data v3 API Key. |
|SkidServerUrl | Skid Command | Skid Server URL. **THIS ISN'T NEEDED FOR YOU SINCE COMMAND `Skid` IS PROPRIETARY**