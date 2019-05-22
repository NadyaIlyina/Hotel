package com.hotel_admin.controller;

import com.hotel_admin.model.*;
import com.hotel_admin.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {
    private UsersService usersService;
    private RoomsService roomsService;
    private CategoriesService categoriesService;
    private ClientService clientService;
    private ContactsService contactsService;
    private GalleryService galleryService;
    private NewsService newsService;
    private PositionService positionService;
    private RegestrationService regestrationService;
    private StaffService staffService;

    public MainController(UsersService usersService, RoomsService roomsService, CategoriesService categoriesService,
                          ClientService clientService, ContactsService contactsService, GalleryService galleryService,
                          NewsService newsService, PositionService positionService, RegestrationService regestrationService,
                          StaffService staffService) {
        this.usersService = usersService;
        this.roomsService = roomsService;
        this.categoriesService = categoriesService;
        this.clientService = clientService;
        this.contactsService = contactsService;
        this.galleryService = galleryService;
        this.newsService = newsService;
        this.positionService = positionService;
        this.regestrationService = regestrationService;
        this.staffService = staffService;
    }

    @GetMapping("/login")
    public String signin(Model model) {
        Collection<CategoryRooms> categories = categoriesService.findAll();
        model.addAttribute("category_rooms", categories);
        return "signin";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/news")
    public String news(Model model) {
        Collection<News> news = newsService.findAll();
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        Collection<Contacts> contacts = contactsService.findAll();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/rooms")
    public String rooms(@RequestParam(name = "category_room", required = false) Integer categoryId, Model model) {
        Collection<CategoryRooms> categories = categoriesService.findAll();
        model.addAttribute("category_rooms", categories);

        Collection<Room> rooms;
        if (categoryId != null) {
            rooms = roomsService.findAllByCategoryId(categoryId);
            CategoryRooms category = categoriesService.findById(categoryId);
            if (category != null)
                model.addAttribute("category", category.getName());
        } else
            rooms = roomsService.findAll();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String roomById(@PathVariable int id, Model model, Principal principal) {
        Collection<CategoryRooms> categories = categoriesService.findAll();
        model.addAttribute("category_rooms", categories);
        Room room = roomsService.findById(id);
        if (room != null) {
            model.addAttribute("room", room);
            Collection<Regestration> regestrationToThisRoom = regestrationService.findAllByRoomId(room.getId());
            model.addAttribute("regestration", regestrationToThisRoom);
        }
        return "room";
    }



}
