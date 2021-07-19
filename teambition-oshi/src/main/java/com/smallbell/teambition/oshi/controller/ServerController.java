package com.smallbell.teambition.oshi.controller;

import com.smallbell.teambition.oshi.util.Server;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 	服务器监控
 */
@RestController
@RequestMapping("/monitor")
public class ServerController {

    /**
     * 	获取服务器监控信息
     * @param
     * @return 
     */
    @RequestMapping(value = "/server",method = RequestMethod.GET)
    public Server getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return server;
    }

}
