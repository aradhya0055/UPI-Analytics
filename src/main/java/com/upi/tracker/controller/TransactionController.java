package com.upi.tracker.controller;

import com.upi.tracker.entity.Transaction;
import com.upi.tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;

    // Dashboard Page - Show all transactions + chart
    @GetMapping("/")
    public String dashboard(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();
        
        // Calculate statistics
        double totalVolume = transactions.stream().mapToDouble(t -> t.getAmount() * (t.getExchangeRate() != null ? t.getExchangeRate() : 1.0)).sum();
        double averageAmount = transactions.isEmpty() ? 0.0 : totalVolume / transactions.size();
        long uniqueCountries = transactions.stream().map(t -> t.getCountry()).distinct().count();
        
        // Calculate monthly expenditure
        Map<String, Double> monthlyExpenditure = calculateMonthlyExpenditure(transactions);
        
        // Create simplified country data for template
        Map<String, String> countryOptions = new LinkedHashMap<>();
        countryOptions.put("India", "🇮🇳 INR");
        countryOptions.put("United States", "🇺🇸 USD");
        countryOptions.put("United Kingdom", "🇬🇧 GBP");
        countryOptions.put("European Union", "🇪🇺 EUR");
        countryOptions.put("Japan", "🇯🇵 JPY");
        countryOptions.put("Australia", "🇦🇺 AUD");
        countryOptions.put("Canada", "🇨🇦 CAD");
        countryOptions.put("Switzerland", "🇨🇭 CHF");
        countryOptions.put("Singapore", "🇸🇬 SGD");
        countryOptions.put("South Korea", "🇰🇷 KRW");
        countryOptions.put("China", "🇨🇳 CNY");
        countryOptions.put("Brazil", "🇧🇷 BRL");
        countryOptions.put("Russia", "🇷🇺 RUB");
        countryOptions.put("South Africa", "🇿🇦 ZAR");
        countryOptions.put("Mexico", "🇲🇽 MXN");
        countryOptions.put("Turkey", "🇹🇷 TRY");
        countryOptions.put("Saudi Arabia", "🇸🇦 SAR");
        countryOptions.put("UAE", "🇦🇪 AED");
        countryOptions.put("Thailand", "🇹🇭 THB");
        countryOptions.put("Malaysia", "🇲🇾 MYR");
        
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalVolume", totalVolume);
        model.addAttribute("averageAmount", averageAmount);
        model.addAttribute("uniqueCountries", uniqueCountries);
        model.addAttribute("countries", countryOptions);
        model.addAttribute("monthlyExpenditure", monthlyExpenditure);
        
        return "dashboard";
    }

    // Handle form submission
    @PostMapping("/add")
    public String addTransaction(
            @RequestParam String senderUpiId,
            @RequestParam String receiverUpiId,
            @RequestParam Double amount,
            @RequestParam String country,
            @RequestParam(required = false) String purpose) {

        // Get currency and exchange rate based on country
        String currency = "INR";
        Double exchangeRate = 1.0;
        
        switch (country) {
            case "United States":
                currency = "USD";
                exchangeRate = 0.012;
                break;
            case "United Kingdom":
                currency = "GBP";
                exchangeRate = 0.0095;
                break;
            case "European Union":
                currency = "EUR";
                exchangeRate = 0.011;
                break;
            case "Japan":
                currency = "JPY";
                exchangeRate = 1.8;
                break;
            case "Australia":
                currency = "AUD";
                exchangeRate = 0.018;
                break;
            case "Canada":
                currency = "CAD";
                exchangeRate = 0.016;
                break;
            case "Switzerland":
                currency = "CHF";
                exchangeRate = 0.010;
                break;
            case "Singapore":
                currency = "SGD";
                exchangeRate = 0.016;
                break;
            case "South Korea":
                currency = "KRW";
                exchangeRate = 16.5;
                break;
            case "China":
                currency = "CNY";
                exchangeRate = 0.087;
                break;
            case "Brazil":
                currency = "BRL";
                exchangeRate = 0.060;
                break;
            case "Russia":
                currency = "RUB";
                exchangeRate = 1.1;
                break;
            case "South Africa":
                currency = "ZAR";
                exchangeRate = 0.22;
                break;
            case "Mexico":
                currency = "MXN";
                exchangeRate = 0.20;
                break;
            case "Turkey":
                currency = "TRY";
                exchangeRate = 0.39;
                break;
            case "Saudi Arabia":
                currency = "SAR";
                exchangeRate = 0.045;
                break;
            case "UAE":
                currency = "AED";
                exchangeRate = 0.044;
                break;
            case "Thailand":
                currency = "THB";
                exchangeRate = 0.43;
                break;
            case "Malaysia":
                currency = "MYR";
                exchangeRate = 0.057;
                break;
        }

        Transaction transaction = Transaction.builder()
                .senderUpiId(senderUpiId)
                .receiverUpiId(receiverUpiId)
                .amount(amount)
                .country(country)
                .currency(currency)
                .exchangeRate(exchangeRate)
                .purpose(purpose != null ? purpose : "")
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
        return "redirect:/";
    }

    private Map<String, Double> calculateMonthlyExpenditure(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                    t -> t.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                    Collectors.summingDouble(t -> t.getAmount() * (t.getExchangeRate() != null ? t.getExchangeRate() : 1.0))
                ));
    }

    // All Transactions Page
    @GetMapping("/transactions")
    public String allTransactions(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }

    // Analytics Page
    @GetMapping("/analytics")
    public String analytics(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();
        
        // Calculate statistics
        double totalVolume = transactions.stream().mapToDouble(t -> t.getAmount() * (t.getExchangeRate() != null ? t.getExchangeRate() : 1.0)).sum();
        long uniqueCountries = transactions.stream().map(t -> t.getCountry()).distinct().count();
        
        // Calculate monthly expenditure
        Map<String, Double> monthlyExpenditure = calculateMonthlyExpenditure(transactions);
        
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalVolume", totalVolume);
        model.addAttribute("uniqueCountries", uniqueCountries);
        model.addAttribute("monthlyExpenditure", monthlyExpenditure);
        
        return "analytics";
    }

    // Delete single transaction
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String, String> deleteTransaction(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            transactionRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "Transaction deleted successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete transaction");
        }
        return response;
    }

    // Clear all transactions
    @DeleteMapping("/clear-all")
    @ResponseBody
    public Map<String, String> clearAllTransactions() {
        Map<String, String> response = new HashMap<>();
        try {
            transactionRepository.deleteAll();
            response.put("status", "success");
            response.put("message", "All transactions cleared successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to clear transactions");
        }
        return response;
    }
}
