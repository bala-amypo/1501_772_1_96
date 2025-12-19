@RestController
@RequestMapping("/api/capacity-alerts")
public class CapacityAlertController {

    private final CapacityAnalysisService service;

    public CapacityAlertController(CapacityAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public CapacityAnalysisResultDto analyze(
            @RequestParam String teamName,
            @RequestParam String start,
            @RequestParam String end
    ) {
        return service.analyzeTeamCapacity(
                teamName,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}
