package me.Derpy.Bosses.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.events.gladiator;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.translate;
import me.Derpy.Bosses.utilities.items.Enchants;
import me.Derpy.Bosses.utilities.items.soul;
import net.md_5.bungee.api.ChatColor;

public class ondeath implements Listener{
	public static MoreBosses plugin;
	private static boolean vex_enabled;
	private static double vex_spawns;
	@SuppressWarnings("static-access")
	public ondeath(MoreBosses plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.vex_enabled = plugin.getConfig().getBoolean("bosses.vex");
		this.vex_spawns = plugin.getConfig().getDouble("spawnrates.vex");
	}
	public static void mount(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.CowEgg")){
			ItemStack egg = new ItemStack(Material.COW_SPAWN_EGG, 1);
			ItemMeta meta = (ItemMeta) egg.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.GOLD+"A mysterious egg", ChatColor.RED+"Contains the soul of a slaughtered cow"));
			meta.setUnbreakable(true);
			meta.addEnchant(Enchantment.QUICK_CHARGE, 1, false);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			egg.setItemMeta(meta);
			event.getDrops().clear();
			event.getDrops().add(egg);
			event.getDrops().add(new ItemStack(Material.SADDLE, 1));
		}
	}
	public static void tier0(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward = (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 0"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*1);
			
			if(vex_enabled) {
				if(Random.random(vex_spawns)) {
					me.Derpy.Bosses.mobs.vex.newvex(event.getEntity().getLocation(), plugin);
				}
			}
		}
	}
	public static void tier1(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward= (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 1"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*2);
			
			if(vex_enabled) {
				if(Random.random(vex_spawns)) {
					me.Derpy.Bosses.mobs.vex.newvex(event.getEntity().getLocation(), plugin);
				}
			}
		}
	}
	public static void tier2(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward= (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			inv.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 2"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*3);
			
			if(vex_enabled) {
				if(Random.random(vex_spawns)) {
					me.Derpy.Bosses.mobs.vex.newvex(event.getEntity().getLocation(), plugin);
				}
			}
		}
	}
	public static void tier3(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward= (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			inv.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			inv.addItemFlags(ItemFlag.HIDE_PLACED_ON);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 3"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*4);
			
			if(vex_enabled) {
				if(Random.random(vex_spawns)) {
					me.Derpy.Bosses.mobs.vex.newvex(event.getEntity().getLocation(), plugin);
				}
			}
		}
	}
	public static void tier4(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward= (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			inv.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			inv.addItemFlags(ItemFlag.HIDE_PLACED_ON);
			inv.addItemFlags(ItemFlag.HIDE_DESTROYS);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 4"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*4);
		}
	}
	public static void tier5(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			ItemStack reward= (ItemStack) new ItemStack(Material.BOOK, 1);
			ItemMeta inv = (ItemMeta) reward.getItemMeta();
			inv.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			inv.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			inv.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			inv.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
			inv.addItemFlags(ItemFlag.HIDE_PLACED_ON);
			inv.addItemFlags(ItemFlag.HIDE_DESTROYS);
			inv.setLore(Arrays.asList(ChatColor.GOLD+translate.get("booklore0", plugin), ChatColor.GOLD+translate.get("booklore1", plugin), ChatColor.GREEN+"Tier 5"));
			inv.setDisplayName(ChatColor.RED+""+ChatColor.MAGIC+"Book of Spoils");
			inv.setUnbreakable(true);
			reward.setItemMeta(inv);
			
			event.getDrops().clear();
			event.getDrops().add(reward);
			event.setDroppedExp(event.getDroppedExp()*4);
		}
	}
	public static void vex(EntityDeathEvent event) {
		if(plugin.getConfig().getBoolean("rewards.Spoils")) {
			event.getDrops().clear();
			if(Random.random(0.5)) {
				event.getDrops().add(soul.get());
			}
			event.setDroppedExp(0);
		}
	}
	@EventHandler
	//Slime final boss large size
	public void onDeath(EntityDeathEvent event) throws InterruptedException {
		final Entity entity = event.getEntity();
		EntityType type = entity.getType();
		if(entity.getWorld().getName().equals("MoreBosses-Colosseum")) {
			if(!(entity instanceof Player)) {
				event.getDrops().clear();
			}
				gladiator.checkwave((Entity) entity);
		}
		if(entity.getWorld().getName().contains("MoreBosses-void")) {
			event.getDrops().clear();
			event.setDroppedExp(0);
			if(entity.getType()==EntityType.WITHER_SKELETON) {
				AreaEffectCloud cloud = (AreaEffectCloud) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.AREA_EFFECT_CLOUD);
				cloud.setColor(Color.BLACK);
				cloud.setDuration(2);
				cloud.setParticle(Particle.TOTEM);
				cloud.setRadius(10);
				cloud.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						AreaEffectCloud cloud = (AreaEffectCloud) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.AREA_EFFECT_CLOUD);
						cloud.setColor(Color.BLACK);
						cloud.setDuration(2);
						cloud.setParticle(Particle.TOTEM);
						cloud.setRadius(10);
						cloud.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
					}
				}, 20L);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						AreaEffectCloud cloud = (AreaEffectCloud) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.AREA_EFFECT_CLOUD);
						cloud.setColor(Color.BLACK);
						cloud.setDuration(2);
						cloud.setParticle(Particle.TOTEM);
						cloud.setRadius(10);
						cloud.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
					}
				}, 40L);
			}else if(entity.getType()==EntityType.BLAZE) {
				ItemStack item = new ItemStack(Material.FIRE_CHARGE);
				ItemMeta meta = item.getItemMeta();
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GOLD+"[Rightclick] to launch a fireball");
				meta.setLore(lore);
				item.setItemMeta(meta);
				event.getDrops().add(item);
			}else if(entity.getType()==EntityType.GHAST) {
				if(plugin.getConfig().getBoolean("ghastevent.active")) {
					ghastevent.defeated();
					event.getDrops().clear();
				}
			}
		}
		// Vex
		if(type==EntityType.VEX) {
			if(entity.isSilent()) {
				ondeath.vex(event);
			}
		}
		// Tier 5
		if(type==EntityType.MAGMA_CUBE) {
			if(entity.isSilent()) {
				if(plugin.getConfig().getBoolean("bosses.smite_death")) {
					entity.getWorld().strikeLightningEffect(entity.getLocation());
				}
				ondeath.tier5(event);
			}
		}
		// TIER 4
		if(type==EntityType.WITHER) {
			if(!(plugin.getConfig().getBoolean("tier4_bosses.Wither_Killed"))) {
				plugin.getConfig().set("tier4_bosses.Wither_Killed", true);
				plugin.saveConfig();
			}
			if(entity.isSilent()) {
					if(plugin.getConfig().getBoolean("experimental.Gems")) {
						ondeath.tier4(event);
						entity.getWorld().strikeLightningEffect(entity.getLocation());
						if(!(plugin.getConfig().getBoolean("tier4_bosses.Undeadwither_Killed"))) {
							plugin.getConfig().set("tier4_bosses.Undeadwither_Killed", true);
							plugin.saveConfig();
							Block block = entity.getWorld().getBlockAt((int) entity.getLocation().getX(), (int) entity.getLocation().getY()+10, (int) entity.getLocation().getZ());
							block.setType(Material.END_GATEWAY);
							entity.getWorld().playSound(entity.getLocation(), Sound.MUSIC_DISC_11, 600, (float) 0.5);
							Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.ITALIC+""+ChatColor.BOLD+translate.get("breach", plugin));
						}else {
							Block block = entity.getWorld().getBlockAt((int) entity.getLocation().getX(), (int) entity.getLocation().getY()+10, (int) entity.getLocation().getZ());
							block.setType(Material.END_GATEWAY);
							entity.getWorld().playSound(entity.getLocation(), Sound.MUSIC_DISC_11, 600, (float) 0.5);
						}
					}
			}
		}
		if(type==EntityType.ENDERMITE) {
			if(Random.random(0.005)) {
				event.getDrops().add(new ItemStack(Enchants.mites()));
			}
		}
		if(type==EntityType.ENDER_DRAGON) {
			if(!(plugin.getConfig().getBoolean("tier4_bosses.Enderdragon_Killed"))) {
				plugin.getConfig().set("tier4_bosses.Enderdragon_Killed", true);
				plugin.saveConfig();
			}
			if(entity.isSilent()) {
				ondeath.tier4(event);
				event.getDrops().add(new ItemStack(Material.DRAGON_EGG));
				if(plugin.getConfig().getBoolean("bosses.smite_death")) {
					entity.getWorld().strikeLightningEffect(entity.getLocation());
				}
			}
		}else if(type==EntityType.WITHER_SKELETON||type==EntityType.ELDER_GUARDIAN||type==EntityType.SLIME) {
			// TIER 3
			if(entity.isSilent()) {
					if(!(type==EntityType.SLIME)) {
						ondeath.tier3(event);
						if(plugin.getConfig().getBoolean("bosses.smite_death")) {
							entity.getWorld().strikeLightningEffect(entity.getLocation());
						}
					}else {
						if(((Slime) entity).getSize()==5) {
							ondeath.tier3(event);
							if(plugin.getConfig().getBoolean("bosses.smite_death")) {
								entity.getWorld().strikeLightningEffect(entity.getLocation());
							}
						}
					}
			}
		}
		// TIER 2
		if(type==EntityType.CREEPER || type==EntityType.BLAZE || type==EntityType.GUARDIAN || type==EntityType.STRAY) {
			if(entity.isSilent()) {
					ondeath.tier2(event);
					if(plugin.getConfig().getBoolean("bosses.smite_death")) {
						entity.getWorld().strikeLightningEffect(entity.getLocation());
					}
			}
		}
		// TIER 1
		if(type==EntityType.ZOMBIE || type==EntityType.SKELETON || type==EntityType.DROWNED) {
			if(entity.isSilent()){
//					ondeath.tier1(event);
					ondeath.tier1(event);
					if(plugin.getConfig().getBoolean("bosses.smite_death")) {
						entity.getWorld().strikeLightningEffect(entity.getLocation());
					}
					if(entity.isInsideVehicle()) {
						if(entity.getVehicle() instanceof Cow) {
							entity.getVehicle().remove();
						}
					}
			}
		//TIER 0
		}else if(type==EntityType.COW) {
			if(!(entity.getPassengers().isEmpty())) {
				if(entity.getPassengers().get(0).getType()==EntityType.ZOMBIE || entity.getPassengers().get(0).getType()==EntityType.HUSK || entity.getPassengers().get(0).getType()==EntityType.SKELETON) {
						entity.getPassengers().get(0).remove();
						if(entity.isSilent()) {
								ondeath.tier0(event);
								if(plugin.getConfig().getBoolean("bosses.smite_death")) {
									entity.getWorld().strikeLightningEffect(entity.getLocation());
								}
						}
				}
			}
		}else if(type==EntityType.PIG || type==EntityType.SHEEP || type==EntityType.TROPICAL_FISH || type==EntityType.PHANTOM) {
			if(entity.isSilent()){
					ondeath.tier0(event);
					if(plugin.getConfig().getBoolean("bosses.smite_death")) {
						entity.getWorld().strikeLightningEffect(entity.getLocation());
					}
					if(entity.getPassengers().size()>0) {
						for(Entity entity2 : entity.getPassengers()) {
							entity2.remove();
						}
					}
			}
		}
	}

}
