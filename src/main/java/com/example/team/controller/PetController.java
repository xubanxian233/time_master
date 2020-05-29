package com.example.team.controller;

import com.example.team.pojo.Pet;
import com.example.team.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Map;

@Controller
@RequestMapping("/pet")
public class PetController extends BaseController {
    @Autowired
    private PetService petService;

    /**
     * getPet 获取宠物
     *
     * @param
     * @return Pet 宠物
     **/
    @RequestMapping("/getPet")
    @ResponseBody
    public Pet getPet() {
        String userId = request.getHeader("id");
        Pet pet = petService.getPetByUserId(Integer.parseInt(userId));
        return pet;
    }

    /**
     * updatePet 修改宠物信息
     *
     * @param param 名字或者皮肤
     * @return Pet 宠物
     **/
    @RequestMapping(value = "/updatePet",method = RequestMethod.POST)
    @ResponseBody
    public Pet updatePet(@RequestBody Map<String, Object> param) {
        Object name = param.get("name");
        Object skin = param.get("skin");
        int userId = Integer.parseInt(request.getHeader("id"));
        Pet pet = petService.getPetByUserId(userId);
        if (name != null) {
            pet.setName(name.toString());
        } else if (skin != null) {
            pet.setSkinId(Integer.parseInt(skin.toString()));
        }
        petService.update(pet);
        return pet;
    }
}
