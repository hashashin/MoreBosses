package me.Derpy.Bosses.listeners;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
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

import me.Derpy.Bosses.Main;
import me.Derpy.Bosses.events.ghastevent;
import me.Derpy.Bosses.mobs.tier5.Magma;
import me.Derpy.Bosses.utilities.GetRewards;
import me.Derpy.Bosses.utilities.inventories;
import me.Derpy.Bosses.utilities.translate;
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
	private Main plugin;
	public Use(Main plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}
	@EventHandler
	public void onPlayerUse(final PlayerInteractEvent event) throws IllegalArgumentException, IOException {
		Player p = event.getPlayer();
		if(event.getAction()==Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType()==Material.END_GATEWAY) {
				if(!(event.getClickedBlock().getBiome().name().toLowerCase().contains("end"))) {
					if(!(event.getItem()==null)) {
						if(event.getItem().getType()==Material.EMERALD) {
							if(event.getItem().getItemMeta().isUnbreakable()) {
								if(event.getItem().getItemMeta().getDisplayName().contains("Gem of")) {
									if(event.getItem().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
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
								if(!(plugin.getConfig().getBoolean("ghastevent.active"))) {
									plugin.getConfig().set("ghastevent.active", true);
									plugin.saveConfig();
									event.getItem().setAmount(event.getItem().getAmount()-1);
									Bukkit.getServer().broadcastMessage(ChatColor.RED+"The ghast realm is opening, breach will open in 5 minutes");
									Bukkit.getServer().broadcastMessage(ChatColor.RED+Double.toString(event.getClickedBlock().getLocation().getX())+", "+Double.toString(event.getClickedBlock().getLocation().getY())+", "+Double.toString(event.getClickedBlock().getLocation().getZ()));
									plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
										  public void run() {
											  ArmorStand stand = (ArmorStand) event.getClickedBlock().getWorld().spawnEntity(event.getClickedBlock().getLocation(), EntityType.ARMOR_STAND);
										      stand.setVisible(false);
										      stand.setGravity(false);
										      stand.setInvulnerable(true);
										      ArrayList<Player> Playerlist = new ArrayList<Player>();
										      Boolean users = false;
										      for(Entity entity : stand.getNearbyEntities(25, 25, 25)) {
										    	  if(entity instanceof Player) {
										    		  Player p = (Player) entity;
										    		  Playerlist.add(p);
										    		  users = true;
										    	  }
										      }
										      stand.remove();
										      if(users) {
										    	  ghastevent.start(Playerlist);
										      }
										 }
//									}, 6000L);
									}, 20L);
								}else {
									event.getPlayer().sendMessage(ChatColor.RED+"A boss fight is already in progress");
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
			if(p.getInventory().getItemInMainHand().getType()==Material.BOOK) {
				if(p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
					if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE+"Recipe")) {
						if(p.getInventory().getItemInMainHand().hasItemMeta()) {
							if(p.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {
								String lore = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(0);
								if(lore.equals(ChatColor.BLUE+"Cursed Diamond")) {			
									p.openInventory(inventories.curserecipe(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Stick")) {
									p.openInventory(inventories.ichorstick(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Boots")) {
									p.openInventory(inventories.ichorboots(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Leggings")) {
									p.openInventory(inventories.ichorlegs(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Chestplate")) {
									p.openInventory(inventories.ichorchest(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Helmet")) {
									p.openInventory(inventories.ichorhelm(plugin));
								}else if(lore.equals(ChatColor.GOLD+"Ichor Sword")) {
									p.openInventory(inventories.ichorsword(plugin));
								}else if(lore.equals("Elytra")) {
									p.openInventory(inventories.elytra(plugin));
								}else if(lore.equals("Ghast Helmet")) {
									p.openInventory(inventories.ghasthelm(plugin));
								}else if(lore.equals("Ghast Tunic")) {
									p.openInventory(inventories.ghastchest(plugin));
								}else if(lore.equals("Ghast Leggings")) {
									p.openInventory(inventories.ghastlegs(plugin));
								}else if(lore.equals("Ghast Boots")) {
									p.openInventory(inventories.ghastboots(plugin));
								}
							}
						}
					}else {
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
											prize.addItem((ItemStack) GetRewards.getreward_tier5(plugin));
											p.openInventory(prize);
										}else if(meta.getLore().get(2).equals(ChatColor.GREEN+"Tier 4")){
											//tier4
											p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
											Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
											
											// Give rewards
											prize.addItem((ItemStack) GetRewards.getreward_tier4(p));
											prize.addItem(new ItemStack(Material.NETHER_STAR));
											p.openInventory(prize);
										}
									//tier 3
									}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES, HIDE_PLACED_ON, HIDE_POTION_EFFECTS]")) {
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier3(p));
										p.openInventory(prize);
									// tier 2
									}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES, HIDE_POTION_EFFECTS]")) {
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier2(p));
										p.openInventory(prize);
									// tier 1
									}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS, HIDE_ATTRIBUTES]")) {
			//							p.getInventory().setItemInMainHand(new ItemStack(Material.AIR, 1));
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										prize.addItem((ItemStack) GetRewards.getreward_tier1(p));
										p.openInventory(prize);
			//							prize.setItem(4 , new ItemStack(Material.DIAMOND, num));
									//tier 0
									}else if(meta.getItemFlags().toString().equals("[HIDE_ENCHANTS]")) {
										p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
										Inventory prize = Bukkit.createInventory(null, 9, ChatColor.RED+"Spoils");
										
										// Give rewards
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
										prize.addItem((ItemStack) GetRewards.getreward_tier0());
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
	
}
