package com.epam.hotelgrodnoinn.service;

import java.io.IOException;

public interface ICommandService {
    String getCommandRole(String command) throws IOException;
}
