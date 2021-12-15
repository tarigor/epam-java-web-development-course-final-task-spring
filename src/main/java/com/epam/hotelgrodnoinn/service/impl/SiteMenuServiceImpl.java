package com.epam.hotelgrodnoinn.service.impl;

import com.epam.hotelgrodnoinn.entity.Menu;
import com.epam.hotelgrodnoinn.types.MenuItemDescription;
import com.epam.hotelgrodnoinn.types.MenuRole;
import com.epam.hotelgrodnoinn.utility.JsonFileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides the functionality of getting a specific menu item based on factory pattern.
 */
@Component
public class SiteMenuServiceImpl {

    public static final String JSON = "./src/main/resources/factory/menu.json";
    private final JsonFileHandler jsonFileHandler;
    HashMap<String, Menu> menuList;

    public SiteMenuServiceImpl(@Value(value = JSON) JsonFileHandler jsonFileHandler) {
        this.jsonFileHandler = jsonFileHandler;
    }

    @PostConstruct
    public void init() throws IOException {
        menuList = new HashMap<>();
        for (MenuItemDescription menuItem : MenuItemDescription.values()) {
            menuList.put(menuItem.name(), getMenu(menuItem.name()));
        }
    }

    public ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole) {
        ArrayList<Menu> sortedMenuListByRole = new ArrayList<>();
        for (MenuRole singleMenuRole : menuRole) {
            for (Map.Entry<String, Menu> entry : this.menuList.entrySet()) {
                if (entry.getValue().getRole().equals(singleMenuRole)) {
                    sortedMenuListByRole.add(entry.getValue());
                }
            }
        }
        return sortedMenuListByRole.stream()
                .sorted(Comparator.comparing(o -> ((Integer) o.getId())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Menu getMenu(String menuItem) throws IOException {
            int id = Integer.parseInt(((LinkedHashMap)(jsonFileHandler.getMapFromJson().get(menuItem))).get("id").toString());
            String pageCommandName = ((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get("command").toString();
            MenuItemDescription menuItemDescription = MenuItemDescription.valueOf(((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get("menuItemDescription").toString());
            MenuRole menuRole = MenuRole.valueOf(((LinkedHashMap) jsonFileHandler.getMapFromJson().get(menuItem)).get("role").toString());
            return new Menu(id, pageCommandName, menuItemDescription, menuRole);
    }
}
