package top.zyqin.spring.boot.basic.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.zyqin.spring.boot.basic.controller.dto.AjaxResponse;
import top.zyqin.spring.boot.basic.controller.dto.Param;
import top.zyqin.spring.boot.basic.entity.Book;
import top.zyqin.spring.boot.basic.entity.BookReader;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * @authoradmin
 * @date 2021/3/5 2:56
 * @description BookController
 */
@RestController
@RequestMapping(value = "api/v1/books")
@Slf4j
public class BookController {

    @GetMapping("all")
    public AjaxResponse selectBooks(){
        BookReader[] readers={
                BookReader.builder()
                        .name("ddd")
                        .age(4)
                        .build(),
                BookReader.builder()
                        .name("ggg")
                        .age(20)
                        .build(),
        };
        List<BookReader> readerList= Arrays.asList(readers);
        Book book1= Book.builder()
                .id(111)
                .author("zyq")
                .title("SpringBoot")
                .content("SpringBoot入门")
                .createTime(new Date())
                .readers(readerList)
                .build();

        Book book2= Book.builder()
                .id(123)
                .author("zyq")
                .title("Vue.js")
                .content("Vue.js入门")
                .createTime(new Date())
                .readers(readerList)
                .build();
        Book[] books={book1,book2};
        List<Book> booklist=Arrays.asList(books);

        return AjaxResponse.success(booklist);
    }

    @GetMapping("{id}")
    public AjaxResponse getBook(@PathVariable int id){
        Book book= Book.builder()
                .id(id)
                .author("zyqin")
                .title("java")
                .content("java")
                .createTime(new Date())
                .build();
        return AjaxResponse.success(book);
    }

    @PostMapping()
    public AjaxResponse saveBook(@RequestBody Book book){
        log.info("saveBook:"+book);
        return AjaxResponse.success(book);
    }

//    @PutMapping()    //修改,通过问号传参==6
//    public  AjaxResponse updateBook(@RequestParam int id,@RequestParam String title){
//        Book book= Book.builder()
//                .id(id)
//                .author("zyq")
//                .title("java")
//                .content("java")
//                .createTime(new Date())
//                .build();
//        book.setTitle(title);
//        log.info("book:"+book);
//        return AjaxResponse.success(book);
//    }
@PutMapping()
public AjaxResponse updateBook(@RequestBody Book book) {
    Book book1 = Book.builder()
            .id(111)
            .author("zyq")
            .title("Java")
            .content("Java")
            .createTime(new Date())
            .build();
    log.info("book:" + book1);

    book1.setId(book.getId());
    book1.setTitle(book.getTitle());

    log.info("book:" + book1);
    return AjaxResponse.success(book1);
}
//    //删除
//    @DeleteMapping("{id}")
//    public  AjaxResponse deleteBook(@PathVariable int id,String title){
//        log.info("id:"+id);
//        return AjaxResponse.success();
//    }

    //删除,表单请求
//    @DeleteMapping()
////    public  AjaxResponse deleteBook(@RequestParam int id,@RequestParam String title){
//        public  AjaxResponse deleteBook(@RequestParam(value ="id",defaultValue = "888") int idd,@RequestParam("tit") String tit){
//        log.info("id:"+idd);
//        log.info("title:"+tit);
//        return AjaxResponse.success();
//    }
//    @DeleteMapping() //与以下同理
//    @RequestMapping(value = "del",method = RequestMethod.DELETE)
//    public AjaxResponse deleteBook(@RequestBody Param param){
//        log.info("id:"+param.getId());
//        log.info("title:"+param.getTitle());
//        return AjaxResponse.success(param);
//    }
    @DeleteMapping()
    public AjaxResponse deleteBook(@RequestBody Param param){
        log.info("id:" + param.getId());
        log.info("title:"+param.getTitle());
        return AjaxResponse.success(param);
    }
}
