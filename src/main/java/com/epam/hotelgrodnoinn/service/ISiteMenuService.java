package com.epam.hotelgrodnoinn.service;

import com.epam.hotelgrodnoinn.entity.Menu;
import com.epam.hotelgrodnoinn.types.MenuRole;

import java.io.IOException;
import java.util.ArrayList;

public interface ISiteMenuService {

    ArrayList<Menu> getMenuListCollectedByRoleSortedByID(MenuRole... menuRole);

    Menu getMenu(String menuItem) throws IOException;
}
