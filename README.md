<div align="center">
<h2>Discord Bot project.</h2>
</div>

<div align="center">
created by inao, LiquidDev and vapor.
</div>

<h3>
What is this project?
</h3>


This project is just a simple Discord bot, coded in Java. Why am I sharing it? Well, I'm sharing it because I think this is bot that can be useful to someone (googled or someone from Nulled ;) ).
Can you use this bot? Absolutely! That's why it's config.yml there! Just please, keep me and my fellows in command 'About' since we have a lot of time on this bot. Thank you :) (btw, it's even in the license).


<br>

<h3>
How to setup this bot
</h3>


1. Setup example-config.yml. If you aren't going to use some commands, you can easily turn them off (and also keep API keys empty, if they are needed by command you are planning to turn off. More in this documentation). *KEEP DATATYPES AS SEEN IN EXAMPLE-CONFIG!*
2. After setting up, rename example-config.yml to config.yml.
3. Keep config.yml and .jar in the same directory!
4. Now, you can launch with command `java -jar <bot>.jar`

<div class="panel panel-danger">
**Danger**
{: .panel-heading}
<div class="panel-body">
Don't touch anything you aren't sure about. For example, option 'production'
</div>
</div>

<h3>
Commands and settings
</h3>

|  Command |  Setting |  Options |  APIs | Needed Perms | Messages |
|---|---|---|---|---|---|
| About | None  | None  | None  | None  | None |
| Antipihoda | Antipihoda | antiGroup | None | None | AntipihodaInGroup, AntipihodaJoined |
| Approve | Approve | None | None | Manage Roles (Server) | GenericNoPerms, ApproveAlready |
| Count | Count | None | None | Create Channels (Server) | GenericNoPerms, GenericFail, GenericArgsErr, CountgameCreated, CountgameClosed, CountgameCompleted |
| Delete | None | None | None | Kick Users (From Server) | None |
| GenKey | ExternalServer | None | None | Server Admin | KeyGenerated |
| Help | None | None | None | None | None |
| Mute | Mute | None | None | Mute Users (Server) | GenericNoPerms, GenericArgsErr, MuteSuccess |
| Nicksgen | Nicksgen | None | None | None | GenericArgsErr, NicksgenErrNums, NicksgenLimit, NicksgenMinus, NicksGenSuccess |
| Ping | production | None | None | None | None |


<h3>
Commands description
</h3>

1. About - Command that contains copyright info _cannot be disabled_
2. Antipihoda - 