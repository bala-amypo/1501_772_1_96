@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(UserAccountRepository repo,
                           BCryptPasswordEncoder encoder,
                           JwtTokenProvider tokenProvider) {
        this.repo = repo;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResponse authenticate(AuthRequest req) {

        UserAccount user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        String token = tokenProvider.generateToken(user);
        return new AuthResponse(token, user.getId());
    }
}
