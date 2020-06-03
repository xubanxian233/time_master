package com.example.team.controller;

import com.example.team.pojo.Pet;
import com.example.team.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/pet")
public class PetController extends BaseController {
    @Autowired
    private PetService petService;

    @RequestMapping("/getPet")
    @ResponseBody
    /**
     * @description: 获取宠物信息
     * @Param: []
     * @return: com.example.team.pojo.Pet
     * @update: time: 2020/6/3 8:56
     */
    public Pet getPet() {
        String userId = request.getHeader("id");
        Pet pet = petService.getPetByUserId(Integer.parseInt(userId));
        return pet;
    }


    @RequestMapping(value = "/updatePet", method = RequestMethod.POST)
    @ResponseBody
    /**
     * @description: 修改宠物
     * @Param: [param]
     * @return: com.example.team.pojo.Pet
     * @update: time: 2020/6/3 8:56
     */
    public Pet updatePet(@RequestBody Map<String, Object> param) {
        Object name = param.get("name");
        int userId = Integer.parseInt(request.getHeader("id"));
        Pet pet = petService.getPetByUserId(userId);
        if (name != null) {
            pet.setName(name.toString());
        }
        petService.update(pet);
        return pet;
    }
}
