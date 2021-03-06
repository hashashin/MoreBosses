package me.Derpy.Bosses.listeners;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Illager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Derpy.Bosses.MoreBosses;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.mobs.tier5.Magma;
import me.Derpy.Bosses.utilities.ConfigManager;
import me.Derpy.Bosses.utilities.GetRewards;
import me.Derpy.Bosses.utilities.Random;
import me.Derpy.Bosses.utilities.translate;
import me.Derpy.Bosses.utilities.items.Enchants;
import me.Derpy.Bosses.utilities.items.cursed_diamond;
import me.Derpy.Bosses.utilities.items.ghast_totem;
import net.md_5.bungee.api.ChatColor;


//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftItem;
//import net.minecraft.server.v1_14_R1.EntityPlayer;
//import net.minecraft.server.v1_14_R1.Item;
//import net.minecraft.server.v1_14_R1.PacketPlayOutSetCooldown;
//import net.minecraft.server.v1_14_R1.PlayerConnection;
//import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
//
//EntityPlayer cPlayer = ((CraftPlayer) event.getPlayer()).getHandle();
//PlayerConnection playerConnection = cPlayer.playerConnection;
//Item item = Item.getById(event.getPlayer().getInventory().getItemInMainHand().getType().getId());
//PacketPlayOutSetCooldown itemPacket = new PacketPlayOutSetCooldown(item, 100);
//playerConnection.sendPacket(itemPacket);

public class Use implements Listener{
	private MoreBosses plugin;
	public Use(MoreBosses plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerUse(final PlayerInteractEvent event) throws IllegalArgumentException, IOException {
		Player p = event.getPlayer();
		if(event.getAction()==Action.PHYSICAL) {
			if(p.getWorld().getName().equals("MoreBosses-Colosseum")) {
				if(event.getClickedBlock().getType()==Material.BIRCH_PRESSURE_PLATE) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100, 1));
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_RESONATE, 10, 1);
					for(Entity ent : p.getNearbyEntities(100, 100, 100)) {
						if(ent instanceof Illager) {
							((Illager) ent).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20*10, 0));
						}
					}
				}
			}
		}
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType()==Material.END_GATEWAY) {
				if(!(event.getClickedBlock().getBiome().name().toLowerCase().contains("end"))) {
					if(!(event.getItem()==null)) {
						if(event.getItem().getType()==Material.EMERALD) {
							if(event.getItem().getItemMeta().isUnbreakable()) {
								if(event.getItem().getItemMeta().getDisplayName().contains("Gem of")) {
									if(event.getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
										if(plugin.getConfig().getBoolean("tier4_bosses.Undeadwither_Killed")) {
											event.getItem().setAmount(event.getItem().getAmount()-1);
											plugin.getConfig().set("breach.amount", plugin.getConfig().getInt("breach.amount")+1);
											if(!(plugin.getConfig().getInt("breach.amount")==plugin.getConfig().getInt("breach.max"))) {
												Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+translate.get("breachsa", plugin)+"\n"+plugin.getConfig().getInt("breach.amount")+"/"+plugin.getConfig().getInt("breach.max"));
											}
											if(plugin.getConfig().getInt("breach.amount")==plugin.getConfig().getInt("breach.max")) {
												plugin.getConfig().set("breach.amount", 0);
												plugin.getConfig().set("tier4_bosses.Undeadwither_Killed", false);
												Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+translate.get("breachend", plugin));
												event.getClickedBlock().breakNaturally();
											}
											plugin.saveConfig();
										}else {
											event.getItem().setAmount(event.getItem().getAmount()-1);
											event.getClickedBlock().breakNaturally();
										}
									}
								}
							}
						}else if(event.getItem().getType()==Material.DIAMOND) {
							if(event.getItem().getItemMeta().isUnbreakable()) {
								if(event.getItem().equals(cursed_diamond.get(plugin))) {
									if(event.getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
										event.getItem().setAmount(event.getItem().getAmount()-1);
										LivingEntity entity = (LivingEntity) event.getPlayer().getWorld().spawnEntity(new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getX(),event.getPlayer().getLocation().getY()+40,event.getPlayer().getLocation().getZ()), EntityType.MAGMA_CUBE);
										Magma.newmagma(entity, plugin);
										Bukkit.getServer().broadcastMessage(ChatColor.RED+""+ChatColor.BOLD+""+ChatColor.ITALIC+translate.get("magmas", plugin));
									}
								}
							}
						}else if(event.getItem().getType()==Material.TOTEM_OF_UNDYING) {
							if(event.getItem().isSimilar(ghast_totem.get(plugin))) {
								if(plugin.getConfig().getBoolean("worlds.enabled_worlds.MoreBossesVoid")) {
									if(!(plugin.getConfig().getBoolean("ghastevent.active"))) {
										plugin.getConfig().set("ghastevent.active", true);
										plugin.saveConfig();
										event.getItem().setAmount(event.getItem().getAmount()-1);
										Bukkit.getServer().broadcastMessage(ChatColor.RED+"The ghast realm is opening, breach will open in 5 minutes");
										AreaEffectCloud cloud = (AreaEffectCloud) event.getClickedBlock().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.AREA_EFFECT_CLOUD);
										cloud.setRadius(10);
										cloud.setFallDistance(1);
	//									cloud.setParticle(Particle.ENCHANTMENT_TABLE);
										cloud.setParticle(Particle.ENCHANTMENT_TABLE);
										cloud.setDuration(6000);
										Bukkit.getServer().broadcastMessage(ChatColor.RED+Double.toString(event.getClickedBlock().getLocation().getX())+", "+Double.toString(event.getClickedBlock().getLocation().getY())+", "+Double.toString(event.getClickedBlock().getLocation().getZ()));
										plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
											  public void run() {
												  ArmorStand stand = (ArmorStand) event.getClickedBlock().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.ARMOR_STAND);
											      stand.setVisible(false);
											      stand.setGravity(false);
											      stand.setInvulnerable(true);
											      ArrayList<Player> Playerlist = new ArrayList<Player>();
											      Boolean users = false;
											      for(Entity entity : stand.getNearbyEntities(10, 10, 10)) {
											    	  if(entity instanceof Player) {
											    		  Player p = (Player) entity;
											    		  Playerlist.add(p);
											    		  users = true;
											    	  }
											    	  if(entity instanceof AreaEffectCloud) {
											    		  entity.remove();
											    	  }
											      }
											      stand.remove();
											      if(users) {
											    	  ghastevent.start(Playerlist);
											      }
											 }
	//									}, 6000L);
										}, 6000);
									}else {
										event.getPlayer().sendMessage(ChatColor.RED+"A boss fight is already in progress");
									}
								}
							}
						}
					}
				}
			}
		}
		if(event.getAction()==Action.RIGHT_CLICK_AIR || event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			if(p.getInventory().getItemInMainHand().getType()==Material.FIRE_CHARGE) {
				if(p.getWorld().getName().contains("MoreBosses")) {
					if(p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
						if(p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).equals(ChatColor.GOLD+"[Rightclick] to launch a fireball")) {
							p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
							p.launchProjectile(Fireball.class).setYield(10);;
						}
					}
				}
			}
			if(p.getInventory().getItemInMainHand().getType()==Material.KNOWLEDGE_BOOK) {
				p.discoverRecipes(((KnowledgeBookMeta) p.getInventory().getItemInMainHand().getItemMeta()).getRecipes());
			}
			if(p.getInventory().getItemInMainHand().getType()==Material.BOOK) {
				if(p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
					if(p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
						ItemStack item = p.getInventory().getItemInMainHand();
						ItemMeta meta = item.getItemMeta();
						if(meta.getLore().get(0)!=null && meta.getLore().get(1)!=null) {
							if(meta.isUnbreakable()) {
								if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_PLACED_ON, HIDE_POTION_EFFECTS]")) {
									if(meta.getLore().get(2).equals(ChatColor.GREEN+"Tier 5")) {
										// tier 5
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier5")) {
											if(Random.random(0.5)) {
												prize.addItem(new ItemStack(Enchants.smelt()));
											}else if(Random.random(0.10)){
												prize.addItem(new ItemStack(Enchants.smelt(2)));
											}else if(Random.random(0.03)) {
												prize.addItem(new ItemStack(Enchants.smelt(3)));
											}else if(Random.random(0.0125)) {
												prize.addItem(new ItemStack(Enchants.smelt(4)));
											}
											prize.addItem((ItemStack) GetRewards.getreward_tier5(plugin));
										}else {
											@SuppressWarnings("unchecked")
											ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier5");
											while(a.remove(null)) {};
											prize.addItem(a.get(Random.random(a)));
											prize.addItem(a.get(Random.random(a)));
										}
										p.openInventory(prize);
									}else if(meta.getLore().get(2).equals(ChatColor.GREEN+"Tier 4")){
										//tier4
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier4")) {
											prize.addItem((ItemStack) GetRewards.getreward_tier4(p));
											prize.addItem(new ItemStack(Material.NETHER_STAR));
										}else {
											@SuppressWarnings("unchecked")
											ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier4");
											while(a.remove(null)) {};
											prize.addItem(a.get(Random.random(a)));
											prize.addItem(a.get(Random.random(a)));
										}
										p.openInventory(prize);
									}
								//tier 3
								}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES, HIDE_PLACED_ON, HIDE_POTION_EFFECTS]")) {
									p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
									Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
									
									// Give rewards
									if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier3")) {
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
									}else {
										@SuppressWarnings("unchecked")
										ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier2");
										while(a.remove(null)) {};
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
									}
									p.openInventory(prize);
								// tier 2
								}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES, HIDE_POTION_EFFECTS]")) {
									p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
									Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
									
									// Give rewards
									if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier2")) {
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
									}else {
										@SuppressWarnings("unchecked")
										ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier2");
										while(a.remove(null)) {};
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
									}
									p.openInventory(prize);
								// tier 1
								}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES]")) {
		//							p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
									p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
									Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
									
									// Give rewards
									if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier1")) {
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
									}else {
										@SuppressWarnings("unchecked")
										ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier1");
										while(a.remove(null)) {};
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
									}
									p.openInventory(prize);
		//							prize.setItem(4 , new ItemStack(Material.DIAMOND, num));
								//tier 0
								}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS]")) {
									p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
									Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
									
									// Give rewards
									if(!plugin.getConfig().getBoolean("bosses.custom_loot_pools.tier0")) {
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
									}else {
										@SuppressWarnings("unchecked")
										ArrayList<ItemStack> a = (ArrayList<ItemStack>) ConfigManager.pools.getPools().get("MoreBosses.Pool.tier0");
										while(a.remove(null)) {};
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
										prize.addItem(a.get(Random.random(a)));
									}
									p.openInventory(prize);
								}
							}
						}
					}
				}
			}
		}
	}
}
