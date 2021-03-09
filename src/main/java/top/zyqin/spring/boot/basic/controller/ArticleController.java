package top.zyqin.spring.boot.basic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zyqin.spring.boot.basic.controller.dto.AjaxResponse;
import top.zyqin.spring.boot.basic.entity.Article;
import top.zyqin.spring.boot.basic.entity.ArticleReader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @authoradmin
 * @date 2021/3/6 20:12
 * @description ArticleController
 */

@RestController
@RequestMapping(value = "/api/v1/Articles")
@Slf4j
@Api(tags = "文章管理接口")

public class ArticleController {
    /**
     * 查询⽂章，id为URL查询参数
     *
     * @param id ⽂章id
     * @return AjaxResponse
     */
    @ApiOperation("URL传参，根据id获取⽂章")
    @GetMapping()
    public AjaxResponse getArticleByParam(@ApiParam("⽂章 id") @RequestParam("id") int id) {
        ArticleReader[] readers = {

                ArticleReader.builder().name("aaa").age(21).build(),

                ArticleReader.builder().name("bbb").age(20).build()};
        List<ArticleReader> readerList =
                Arrays.asList(readers);
        Article article = Article.builder()
                .id(id)
                .author("zyqin")
                .title("Spring Boot从⼊⻔到精通")
                .content("Spring Boot从⼊⻔到精通")
                .createTime(new Date())
                .readers(readerList)
                .build();
        log.info("article: " + article);
        return AjaxResponse.success(article);
    }
    /**
     * 增加一篇 Article ，使用POST方法（RequestBody 方法接收参数）
     * @param article 文章实体
     * @return AjaxResponse
     */
    @ApiImplicitParam("URl传参新增文章")
    @PostMapping("body")
    public AjaxResponse saveArticle(@RequestBody Article article) {
        log.info("saveArticle" + article);
        return  AjaxResponse.success(article);
    }

    @PostMapping("param")
    public AjaxResponse saveArticle (
            @ApiParam("内容")
            @RequestParam(value = "id",defaultValue = "111",required = false) int id,
            @ApiParam("作者")
            @RequestParam(value = "author",defaultValue = "zyqin",required = false) String author,
            @ApiParam("标题")
            @RequestParam String title,
            @ApiParam("内容")
            @RequestParam String content,
            @ApiParam("创建时间")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(value = "createTime",
                    defaultValue = "2021-03-06 20:20:00", required = false) Date createTime) {
        Article article = Article.builder()
                .id(id)
                .title(title)
                .content(content)
                .author(author)
                .createTime(createTime)
                .build();
        log.info("saveArticle:"+createTime);
        return AjaxResponse.success(article);
    }
    @ApiOperation("表单请求体新增⽂章")
    @PostMapping("form")
    public AjaxResponse saveArticleByFormDate(@RequestParam("formData")String formData) {
        //表单传递数据为字符串
        log.info("formData:" + formData);
        //用jackson的反序列化将字符串转为java对象
        ObjectMapper objectMapper = new ObjectMapper();
        Article article = null;
        try{
            article = objectMapper.readValue(formData,Article.class);
            log.info("article:" + article);
        } catch (JsonProcessingException e ){
            e.printStackTrace();
        }
        return AjaxResponse.success(article);
    }
    @PostMapping("upload")
    public AjaxResponse handleUpload(MultipartFile file, HttpServletRequest request) {
        //创建文件在服务器的存放路径
        String path = request.getServletContext().getRealPath("/upload");
        log.info(path);
        File fileDir = new File(path);
        if(!fileDir.exists()) {
            boolean flag = fileDir.mkdirs();
            log.info("flag:" + flag);
        }
        //文件重命名
        String prefixName = UUID.randomUUID().toString();
        //获取文件后缀名
        String originalFilename = file.getOriginalFilename();
        //
        assert originalFilename != null;
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //拼接新的文件名
        String fileName = prefixName + suffixName;
        log.info(fileName);
        //上传文件
        //创建上传文件对象
        File saveFile = new File(path + "/"+fileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
            AjaxResponse.failure("文件上传失败");
        }
        return AjaxResponse.success("上传成功");
    }
    @PostMapping(value = "/img")
    public AjaxResponse sourceUpload(MultipartFile[] sourceFiles, HttpServletRequest request) {
        List fileNames = new ArrayList();
        YearMonth yearMonth = YearMonth.now();
        Calendar now = Calendar.getInstance();
        for(MultipartFile file :sourceFiles) {
            if(file.isEmpty()) {
                System.out.println("文件为空");
            }

            //文件重命名
            String prefixName = UUID.randomUUID().toString();
            //获取文件后缀名
            String originalFilename = file.getOriginalFilename();
            //
            assert originalFilename != null;
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //拼接新的文件名
            String newFileName = prefixName + suffixName;
            log.info(newFileName);
            //上传文件
            String ym = yearMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            int day = now.get(Calendar.DAY_OF_MONTH);
            String path = request.getServletContext().getRealPath("img/"+ym+"/"+ day + "/" + suffixName);
            log.info(path);
            File fileDir = new File(path);
            if (!fileDir.exists()){
                boolean flag = fileDir.mkdirs();
                log.info("flag:"+flag);
            }
            //创建上传文件对象
            File saveFile = new File(path + "/" + newFileName);
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
                log.info(e.getMessage());
                AjaxResponse.failure("文件上传失败");
            }
            fileNames.add(newFileName);
            log.info(fileNames.toString());
        }
        return AjaxResponse.success("上传成功");
    }
}
