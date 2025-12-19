@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService service;

    public LeaveRequestController(LeaveRequestService service) {
        this.service = service;
    }

    @PostMapping
    public LeaveRequestDto create(@RequestBody LeaveRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequestDto approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequestDto reject(@PathVariable Long id) {
        return service.reject(id);
    }
}
