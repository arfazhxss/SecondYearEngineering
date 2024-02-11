w = linspace(-10, 10, 500);
f = (1j * w + 1) .^ -1;
subplot(2, 1, 1);
plot(w, abs(f));
title('Magnitude');
xlabel('\omega');
ylabel('|F(\omega)|');
subplot(2, 1, 2);
plot(w, unwrap(angle(f)));
title('Argument');
xlabel('\omega');
ylabel('arg F(\omega)');