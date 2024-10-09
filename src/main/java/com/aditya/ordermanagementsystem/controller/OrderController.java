package com.aditya.ordermanagementsystem.controller;

import com.aditya.ordermanagementsystem.entity.Order;
import com.aditya.ordermanagementsystem.response.DataResponse;
import com.aditya.ordermanagementsystem.response.dto.OrderResponseDTO;
import com.aditya.ordermanagementsystem.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        try{
            orderService.placeOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Successfully");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable long id){
        try {
            orderService.updateOrder(order, id);
            return ResponseEntity.ok("Order updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order: " + e.getMessage());
        }
    }

    @PatchMapping("/payment/{id}")
    public ResponseEntity<?> updatePaymentMode(@PathVariable long id, @RequestParam String paymentMode) {
        try{
            Order updatedOrder = orderService.updateOrderPaymentMode(paymentMode, id);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested Id is not valid : "+e.getMessage());
        }
    }

    @GetMapping("order/search")
    public ResponseEntity<?> searchPaginatedOrderByTitleDescription(@RequestParam String keyword,
                                                              @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(value = "size", defaultValue = "2", required = false) int size){
        try{
            List<Order> orders = orderService.searchOrders(keyword, page, size);
            return ResponseEntity.ok(orders);
        }catch (IllegalArgumentException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("order/search/price")
    public ResponseEntity<?> searchOrderByPriceRange(@RequestParam(value = "minPrice", defaultValue = "0.0") double minPrice,
                                                     @RequestParam(value = "maxPrice", defaultValue = "1000000") double maxPrice,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "5") int size){
         try{
             return ResponseEntity.ok(orderService.searchOrderByPriceRange(minPrice, maxPrice, page, size));
         }catch (IllegalArgumentException e){
             return ResponseEntity.badRequest().body("Error in price range search: - Either min Price is greater than max Price, or max price :  " + e.getMessage());
         } catch (Exception e) {
             return ResponseEntity.internalServerError().body(e.getMessage());
         }
    }

    @GetMapping("order/search/brand")
    public ResponseEntity<?> searchOrderByBrand(@RequestParam(value = "brand", defaultValue = "Tech") String brand,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "5") int size){
        try{
            return ResponseEntity.ok(orderService.filterOrdersByBrand(brand, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("order/search/feedback")
    public ResponseEntity<?> searchOrderByFeedback(@RequestParam(value = "brand", defaultValue = "good%quality") String feedback,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "5") int size){
        try{
            return ResponseEntity.ok(orderService.filterOrdersByFeedback(feedback, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("order/search/filter")
    public ResponseEntity<?> searchOrderByFeedbackPriceBrand(@RequestParam(value = "minPrice", defaultValue = "0.0") double minPrice,
                                                             @RequestParam(value = "maxPrice", defaultValue = "1000000") double maxPrice,
                                                             @RequestParam(value = "feedback", defaultValue = "good%quality") String feedback,
                                                             @RequestParam(value = "brand", defaultValue = "Tech") String brand,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "5") int size){
        try{
            return ResponseEntity.ok(orderService.filterOrders(minPrice, maxPrice, feedback, brand, page, size));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("order/search/{id}")
    public ResponseEntity<DataResponse<?>> searchPaginatedOrderByTitleDescription(@PathVariable long id){
        try{
            Order order = orderService.getOrderByID(id);

            // Map the Order entity to the custom DTO
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order);

            // Wrap the DTO in the DataResponse object
            DataResponse<OrderResponseDTO> response = new DataResponse<>(orderResponseDTO);
            return ResponseEntity.ok(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse<>(e.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
