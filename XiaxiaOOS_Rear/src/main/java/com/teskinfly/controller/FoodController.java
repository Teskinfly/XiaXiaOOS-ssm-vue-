package com.teskinfly.controller;

import com.teskinfly.domain.Category;
import com.teskinfly.domain.Food;
import com.teskinfly.pojo.send.DataReturn;
import com.teskinfly.pojo.send.ReturnCode;
import com.teskinfly.pojo.send.TableFood;
import com.teskinfly.pojo.send.TableReturn;
import com.teskinfly.service.impl.CategoryService;
import com.teskinfly.service.impl.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/food")
@CrossOrigin
public class FoodController {
    String picPath = "images/food";
    @Autowired
    FoodService foodService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("findAllFood")
    public TableReturn findAll(HttpServletRequest request) {
        if (categoryService == null) {
            throw new NullPointerException("缺少categoryService");
        }
        List<TableFood> result = new ArrayList<>();
        List<Category> all = categoryService.findAll();
        for (Category category : all) {
            TableFood tableFood = new TableFood(category);
            for (Food food : category.getFoods()) {
                tableFood.addFood(food);
            }
            result.add(tableFood);
        }
        return new TableReturn(result, ReturnCode.SUCCESS);
    }

    @RequestMapping("/addCategory")
    public DataReturn addCategory(@RequestBody Category category) {
        if (category == null) return new DataReturn(ReturnCode.FAIL);
        categoryService.addCategory(category);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/updateCategory")
    public DataReturn updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/deleteCategory")
    public DataReturn deleteCategory(int cid) {
        categoryService.delCategory(cid);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/addFood")
    public DataReturn addFood(@RequestBody Food food) {
        foodService.addFood(food);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/updateFood")
    public DataReturn updateFood(@RequestBody Food food) {
        foodService.updateFood(food);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/deleteFood")
    public DataReturn delFood(int fid) {
        Food byId = foodService.findById(fid);
        String fImg = byId.getFImg();
        File file = new File(picPath + "//" + fImg);
        if (file.isFile()) file.delete();
        foodService.delFood(fid);
        return new DataReturn(ReturnCode.SUCCESS);
    }

    @RequestMapping("/upload")
    public DataReturn upload(@RequestParam("picture") MultipartFile pic, HttpServletRequest request) {
//        System.out.println("i am in");
        String realPath = request.getSession().getServletContext().getRealPath(picPath);
        File filePath = new File(realPath);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String fileName = uuid + "-" + pic.getOriginalFilename();
        try {
            pic.transferTo(new File(realPath, fileName));
            return new DataReturn(ReturnCode.SUCCESS, new ArrayList() {{
                add(picPath + "/" + fileName);
            }});
        } catch (IOException e) {
//            e.printStackTrace();
            return new DataReturn(ReturnCode.FAIL);
        }

    }

    @RequestMapping("getFoodById")
    public DataReturn getFoodById(Integer fid) {
        Food byId = foodService.findById(fid);
        return new DataReturn(ReturnCode.SUCCESS, new ArrayList() {{
            add(byId);
        }});
    }
    @RequestMapping("findAllCategory")
    public List<Category> findAllCategory() {
        return categoryService.findAll();
    }
}
