package com.example.server.Controller;

import com.example.server.Models.Platform;
import com.example.server.Service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/platform")
public class PlatformController {
    public static Logger logger = Logger.getLogger("Platform");
    @Autowired
    private PlatformService platformService;

    @GetMapping("/all")
    @ResponseBody
    List<Platform> getAllPlatform() {
        logger.info("Get All Platform");
        return platformService.getAllPlatform();
    }
}
