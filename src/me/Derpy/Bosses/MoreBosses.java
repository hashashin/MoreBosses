package me.Derpy.Bosses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.Derpy.Bosses.Addons.Utilities.LoadAddonWorlds;
import me.Derpy.Bosses.commands.bspawn;
import me.Derpy.Bosses.commands.debug;
import me.Derpy.Bosses.commands.event;
import me.Derpy.Bosses.commands.language;
import me.Derpy.Bosses.commands.pool;
import me.Derpy.Bosses.commands.saveworld;
import me.Derpy.Bosses.commands.setspawn;
import me.Derpy.Bosses.commands.tp;
import me.Derpy.Bosses.enchants.EnchantmentStorage;
import me.Derpy.Bosses.enchants.LoadEnchants;
import me.Derpy.Bosses.listeners.Use;
import me.Derpy.Bosses.listeners.gemlistener;
import me.Derpy.Bosses.listeners.invclose;
import me.Derpy.Bosses.listeners.ondamage;
import me.Derpy.Bosses.listeners.ondeath;
import me.Derpy.Bosses.listeners.ondismount;
import me.Derpy.Bosses.listeners.onspawn;
import me.Derpy.Bosses.listeners.processcommand;
import me.Derpy.Bosses.mobs.merchants.createNomad;
import me.Derpy.Bosses.utilities.ConfigManager;
import me.Derpy.Bosses.utilities.Refs;
import me.Derpy.Bosses.utilities.UpdateChecker;
import me.Derpy.Bosses.utilities.items.cursed_diamond;
import me.Derpy.Bosses.utilities.items.elytra;
import me.Derpy.Bosses.utilities.items.forge;
import me.Derpy.Bosses.utilities.items.ghast;
import me.Derpy.Bosses.utilities.items.ghast_totem;
import me.Derpy.Bosses.utilities.items.ichor;
import me.Derpy.Bosses.utilities.items.infused_tear;

public class MoreBosses extends JavaPlugin implements Listener {
	public static Boolean Reload = false;
	public static Boolean voidenabled;
	public static Boolean colosseumenabled;
	@Override
	public void onEnable() {
		ConfigManager.setup();
		this.saveResource("config.yml", false);
		voidenabled=this.getConfig().getBoolean("worlds.enabled_worlds.MoreBossesVoid");
		colosseumenabled=this.getConfig().getBoolean("worlds.enabled_worlds.MoreBossesColosseum");
		//Enchantment Loader
		EnchantmentStorage.setfleet(new NamespacedKey(this, "fleet"));
		EnchantmentStorage.setember(new NamespacedKey(this, "ember"));
		EnchantmentStorage.setlifesteal(new NamespacedKey(this, "lifesteal"));
		EnchantmentStorage.setundying(new NamespacedKey(this, "undying"));
		EnchantmentStorage.setreplenish(new NamespacedKey(this, "replenish"));
		EnchantmentStorage.setmites(new NamespacedKey(this, "mites"));
		EnchantmentStorage.setsmelt(new NamespacedKey(this, "smelt"));
		// World Loader
		if(voidenabled) {
			World world = Bukkit.createWorld(new WorldCreator("MoreBosses-void"));
			world.setAutoSave(false);
			world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
			world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
			world.setGameRule(GameRule.MOB_GRIEFING, false);
			world.setGameRule(GameRule.NATURAL_REGENERATION, false);
			world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
			world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
			world.getWorldBorder().setWarningDistance(1000000000);
			world.getWorldBorder().setSize(500);
			world.getWorldBorder().setCenter(world.getSpawnLocation());
			world.setTime(12700);
		}
		if(colosseumenabled) {
			World world2 = Bukkit.createWorld(new WorldCreator("MoreBosses-Colosseum"));
			world2.setAutoSave(false);
			world2.setGameRule(GameRule.DO_MOB_SPAWNING, false);
			world2.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
			world2.setGameRule(GameRule.MOB_GRIEFING, false);
			world2.setGameRule(GameRule.NATURAL_REGENERATION, false);
			world2.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
			world2.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		}
		// Listeners
		PluginManager gm = getServer().getPluginManager();
		onspawn listener = new onspawn(this);
		ondeath listener2 = new ondeath(this);
		ondamage listener4 = new ondamage(this);
		gemlistener listener5 = new gemlistener(this);
		ondismount listener6 = new ondismount(this);
		gm.registerEvents(listener, this);
		gm.registerEvents(listener2, this);
		gm.registerEvents(new Use(this), this);
		gm.registerEvents(listener4, this);
		gm.registerEvents(listener5, this);
		gm.registerEvents(listener6, this);
		gm.registerEvents(new createNomad(), this);
		gm.registerEvents(new processcommand(this), this);
		gm.registerEvents(new invclose(), this);
		EnchantmentStorage.registerenchantsability();
		// Ver checker
		UpdateChecker updater = new UpdateChecker(this, 69355);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]:"+ChatColor.YELLOW+" Thank you for using More Bosses!");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]:"+ChatColor.YELLOW+" Version "+getDescription().getVersion().toString());
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]:"+ChatColor.YELLOW+" Checking for updates...");
		try {
			if (updater.checkForUpdates()) {
				if(!(getDescription().getVersion().toString().contains("TEST_VERSION"))) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]: "+ChatColor.RED+"Plugin is not up to date!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.RED+"Please install the lastest version at https://www.spigotmc.org/resources/more-bosses.69355/");
				}else {
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]: "+ChatColor.RED+"DEV VERSION LOADED");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]: "+ChatColor.RED+"If you should not have access to this version, please immediately contact a developer");
					Refs.setReload(true);
				}
			}else {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]: "+ChatColor.GREEN+"Plugin is up to date!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[MoreBosses]: "+ChatColor.RED+"Failed to connect [Version Checker]");
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.GREEN+"Have fun! :D ~Derpy");
		//Commands
		new bspawn(this);
		new debug(this);
		new event(this);
		new language(this);
		new tp(this);
		new setspawn(this);
		new saveworld(this);
		new pool(this);
		
		//CRAFTING
		cursed_diamond cursed_diamond = new cursed_diamond();
		cursed_diamond.customRecipe();
		ichor ichor = new ichor();
		ichor.customRecipeStick();
		ichor.customRecipeSword();
		ichor.customRecipeBoots();
		ichor.customRecipeChest();
		ichor.customRecipeHelmet();
		ichor.customRecipeLeggings();
		elytra elytra = new elytra();
		elytra.customRecipeElytra();
		forge forge = new forge();
		forge.customRecipe();
		ghast_totem totem = new ghast_totem();
		totem.customRecipe();
		infused_tear tear = new infused_tear();
		tear.customRecipe();
		ghast.tissue_armor ghast = new ghast.tissue_armor();
		ghast.customRecipe_boots();
		ghast.customRecipe_chestplate();
		ghast.customRecipe_helmet();
		ghast.customRecipe_leggings();
		
		//Enchantments
		LoadEnchants.registerEnchantment(EnchantmentStorage.getfleet());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getember());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getlifesteal());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getundying());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getreplenish());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getmites());
		LoadEnchants.registerEnchantment(EnchantmentStorage.getsmelt());
		
		//Addons Enable
		if(gm.getPlugin("NordicReload")!=null) {
			if(gm.isPluginEnabled("NordicReload")) {
				Refs.setNordic(true);
				LoadAddonWorlds.load_nordic();
			}
		}
		
	}
	public void onDisable() {
		if(!(MoreBosses.Reload)) {
			Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Plugin failed to properly disable!");
			Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Attempting proper disable.");
			Bukkit.broadcastMessage(ChatColor.GOLD+"[MoreBosses]: "+ChatColor.YELLOW+""+ChatColor.BOLD+"Ignore this if the server has been stopped (/stop) or restarted (/restart)");
			me.Derpy.Bosses.utilities.BossbarStorage.removeall();
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getfleet());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getember());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getlifesteal());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getundying());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getreplenish());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getmites());
			LoadEnchants.UnloadEnchantment(EnchantmentStorage.getsmelt());
			for(World world : Bukkit.getWorlds()) {
				if(world.getName().startsWith("MoreBosses")) {
					for(Player p : world.getPlayers()) {
						p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
					}
					Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[MoreBosses]: Unloading "+world.getName());
					Bukkit.unloadWorld(world, false);
				}
			}
			getConfig().set("raids.gladiator.active", false);
			getConfig().set("ghastevent.active", false);
			saveConfig();
		}
	}
}
