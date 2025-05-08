package defsu.sc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hiyerarşik menü yapısını tanımlamak ve yönetmek için kullanılan sınıf
 */
public class MenuCore {

    public static void init(){

        MenuCore.addMenuItem("99887766", "Menü lan menü", "/country", "form", "", null, "/icons/48/grid.png");

        MenuCore.addMenuItem("1", "Sistem Yönetimi","/icons/48/grid.png", null);
        MenuCore.addMenuItem("2", "İnsan Kaynakları","/icons/48/grid.png", null);
        MenuCore.addMenuItem("3", "Finans & Muhasebe","/icons/48/grid.png", null);
        MenuCore.addMenuItem("4", "Satış & CRM","/icons/48/grid.png", null);
        MenuCore.addMenuItem("5", "Satın Alma","/icons/48/grid.png", null);
        MenuCore.addMenuItem("6", "Stok & Envanter","/icons/48/grid.png", null);
        MenuCore.addMenuItem("7", "Üretim","/icons/48/grid.png", null);
        MenuCore.addMenuItem("8", "Proje Yönetimi","/icons/48/grid.png", null);
        MenuCore.addMenuItem("9", "Kalite Yönetimi","/icons/48/grid.png", null);
        MenuCore.addMenuItem("10", "Raporlama & İş Zekası","/icons/48/grid.png", null);
        MenuCore.addMenuItem("11", "Lojistik & Sevkiyat","/icons/48/grid.png", null);
        MenuCore.addMenuItem("12", "Varlık & Bakım Yönetimi","/icons/48/grid.png", null);
        MenuCore.addMenuItem("13", "Müşteri Hizmetleri & Portalı","/icons/48/grid.png", null);
        MenuCore.addMenuItem("14", "E-Ticaret & Pazarlama","/icons/48/grid.png", null);
        MenuCore.addMenuItem("15", "Doküman & Yasal Uyum","/icons/48/grid.png", null);

// SİSTEM YÖNETİMİ ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("101", "Genel Tanımlar", "/icons/48/grid.png","1");
        MenuCore.addMenuItem("102", "Kullanıcı Yönetimi", "1");
        MenuCore.addMenuItem("103", "Rol & Yetki Yönetimi", "1");
        MenuCore.addMenuItem("104", "Şirket Yapılandırması", "1");
        MenuCore.addMenuItem("105", "İş Akışı Tanımları", "1");
        MenuCore.addMenuItem("106", "Entegrasyonlar", "1");

// GENEL TANIMLAR ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1001", "Ülkeler", "/country", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1002", "Şehirler", "/city", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1003", "Para Birimleri", "/currency", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1004", "Diller", "/language", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1005", "Ölçü Birimleri", "/units", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1006", "Vergiler", "/tax", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1007", "Bankalar", "/bank", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1008", "Şubeler", "/branch", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1009", "Bölgeler", "/region", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1010", "Döviz Kurları", "/exchange-rates", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1011", "NACE Kodları", "/nace", "form", "", "101", "/icons/16/grid.png");
        MenuCore.addMenuItem("1012", "GTiP Kodları", "/gtip", "form", "", "101", "/icons/16/grid.png");

// KULLANICI YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1101", "Kullanıcılar", "/user", "form", "", "102", "/icons/16/grid.png");
        MenuCore.addMenuItem("1102", "Kullanıcı Grupları", "/usergroup", "form", "", "102", "/icons/16/grid.png");
        MenuCore.addMenuItem("1103", "Yetkilendirme", "/auth", "form", "", "102", "key-icon");
        MenuCore.addMenuItem("1104", "Kullanıcı Etkinlikleri", "/user-activity", "form", "", "102", "activity-icon");
        MenuCore.addMenuItem("1105", "Oturum Yönetimi", "/session", "form", "", "102", "session-icon");
        MenuCore.addMenuItem("1106", "Şifre Politikaları", "/password-policy", "form", "", "102", "password-icon");
        MenuCore.addMenuItem("1107", "İki Faktörlü Doğrulama", "/2fa", "form", "", "102", "2fa-icon");
        MenuCore.addMenuItem("1108", "SSO Yapılandırması", "/sso", "form", "", "102", "sso-icon");

// ROL & YETKİ YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1201", "Roller", "/roles", "form", "", "103", "roles-icon");
        MenuCore.addMenuItem("1202", "İzinler", "/permissions", "form", "", "103", "permission-icon");
        MenuCore.addMenuItem("1203", "Yetki Grupları", "/auth-groups", "form", "", "103", "/icons/16/grid.png");
        MenuCore.addMenuItem("1204", "Rol Atamaları", "/role-assignments", "form", "", "103", "assignment-icon");
        MenuCore.addMenuItem("1205", "Yetki Matrisi", "/auth-matrix", "form", "", "103", "matrix-icon");
        MenuCore.addMenuItem("1206", "Fonksiyon Erişimi", "/function-access", "form", "", "103", "access-icon");

// ŞİRKET YAPILANDIRMASI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1301", "Şirket Bilgileri", "/company-info", "form", "", "104", "company-icon");
        MenuCore.addMenuItem("1302", "Departmanlar", "/departments", "form", "", "104", "department-icon");
        MenuCore.addMenuItem("1303", "Lokasyonlar", "/locations", "form", "", "104", "location-icon");
        MenuCore.addMenuItem("1304", "Maliyet Merkezleri", "/cost-centers", "form", "", "104", "cost-icon");
        MenuCore.addMenuItem("1305", "İş Birimleri", "/business-units", "form", "", "104", "unit-icon");
        MenuCore.addMenuItem("1306", "Finansal Dönemler", "/fiscal-periods", "form", "", "104", "period-icon");
        MenuCore.addMenuItem("1307", "Sistem Parametreleri", "/system-params", "form", "", "104", "params-icon");

// İŞ AKIŞI TANIMLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1401", "İş Akışları", "/workflows", "form", "", "105", "workflow-icon");
        MenuCore.addMenuItem("1402", "Onay Adımları", "/approval-steps", "form", "", "105", "steps-icon");
        MenuCore.addMenuItem("1403", "İş Kuralları", "/business-rules", "form", "", "105", "rules-icon");
        MenuCore.addMenuItem("1404", "Tetikleyiciler", "/triggers", "form", "", "105", "trigger-icon");
        MenuCore.addMenuItem("1405", "Şablonlar", "/workflow-templates", "form", "", "105", "template-icon");
        MenuCore.addMenuItem("1406", "Süreç İzleme", "/process-monitoring", "form", "", "105", "monitor-icon");

// ENTEGRASYONLAR ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("1501", "API Yönetimi", "/api-management", "form", "", "106", "api-icon");
        MenuCore.addMenuItem("1502", "Servis Bağlantıları", "/service-connections", "form", "", "106", "connection-icon");
        MenuCore.addMenuItem("1503", "Veri Aktarımları", "/data-transfers", "form", "", "106", "transfer-icon");
        MenuCore.addMenuItem("1504", "Entegrasyon Logları", "/integration-logs", "form", "", "106", "log-icon");
        MenuCore.addMenuItem("1505", "Webhook Tanımları", "/webhooks", "form", "", "106", "webhook-icon");
        MenuCore.addMenuItem("1506", "Dış Sistemler", "/external-systems", "form", "", "106", "external-icon");
        MenuCore.addMenuItem("1507", "E-Devlet Entegrasyonu", "/e-government", "form", "", "106", "government-icon");

// İNSAN KAYNAKLARI ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("201", "Personel Yönetimi", "2");
        MenuCore.addMenuItem("202", "İşe Alım & Kariyer", "2");
        MenuCore.addMenuItem("203", "Özlük & Bordro", "2");
        MenuCore.addMenuItem("204", "Eğitim & Performans", "2");
        MenuCore.addMenuItem("205", "İSG & Yasal Uyum", "2");

// PERSONEL YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("2001", "Personel Kartları", "/employee-cards", "form", "", "201", "employee-icon");
        MenuCore.addMenuItem("2002", "Organizasyon Şeması", "/org-chart", "form", "", "201", "org-icon");
        MenuCore.addMenuItem("2003", "İzin Yönetimi", "/leave-management", "form", "", "201", "leave-icon");
        MenuCore.addMenuItem("2004", "Vardiya Yönetimi", "/shift-management", "form", "", "201", "shift-icon");
        MenuCore.addMenuItem("2005", "Görev Tanımları", "/job-descriptions", "form", "", "201", "job-icon");
        MenuCore.addMenuItem("2006", "Çalışan Self-Servis", "/employee-self-service", "form", "", "201", "self-icon");
        MenuCore.addMenuItem("2007", "Personel Devamsızlık", "/absence-tracking", "form", "", "201", "absence-icon");

// İŞE ALIM & KARİYER ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("2101", "İş İlanları", "/job-postings", "form", "", "202", "posting-icon");
        MenuCore.addMenuItem("2102", "Aday Havuzu", "/candidate-pool", "form", "", "202", "pool-icon");
        MenuCore.addMenuItem("2103", "Mülakat Süreci", "/interview-process", "form", "", "202", "interview-icon");
        MenuCore.addMenuItem("2104", "Yetenek Yönetimi", "/talent-management", "form", "", "202", "talent-icon");
        MenuCore.addMenuItem("2105", "Kariyer Planlaması", "/career-planning", "form", "", "202", "career-icon");
        MenuCore.addMenuItem("2106", "Oryantasyon", "/orientation", "form", "", "202", "orientation-icon");

// ÖZLÜK & BORDRO ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("2201", "Özlük Dosyaları", "/personal-files", "form", "", "203", "files-icon");
        MenuCore.addMenuItem("2202", "Maaş Hesaplama", "/salary-calculation", "form", "", "203", "salary-icon");
        MenuCore.addMenuItem("2203", "SGK İşlemleri", "/social-security", "form", "", "203", "security-icon");
        MenuCore.addMenuItem("2204", "Vergi İşlemleri", "/tax-transactions", "form", "", "203", "/icons/16/grid.png");
        MenuCore.addMenuItem("2205", "Avanslar", "/advances", "form", "", "203", "advance-icon");
        MenuCore.addMenuItem("2206", "Yan Haklar", "/benefits", "form", "", "203", "benefit-icon");
        MenuCore.addMenuItem("2207", "Kıdem & İhbar", "/severance", "form", "", "203", "severance-icon");

// EĞİTİM & PERFORMANS ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("2301", "Eğitim Planlaması", "/training-planning", "form", "", "204", "planning-icon");
        MenuCore.addMenuItem("2302", "Eğitim Kataloğu", "/training-catalog", "form", "", "204", "catalog-icon");
        MenuCore.addMenuItem("2303", "Eğitim Takibi", "/training-tracking", "form", "", "204", "tracking-icon");
        MenuCore.addMenuItem("2304", "Performans Değerlendirme", "/performance-evaluation", "form", "", "204", "evaluation-icon");
        MenuCore.addMenuItem("2305", "Hedef Yönetimi", "/goal-management", "form", "", "204", "goal-icon");
        MenuCore.addMenuItem("2306", "Yetkinlik Yönetimi", "/competency-management", "form", "", "204", "competency-icon");
        MenuCore.addMenuItem("2307", "Çalışan Anketleri", "/employee-surveys", "form", "", "204", "survey-icon");

// İSG & YASAL UYUM ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("2401", "İSG Eğitimleri", "/ohs-trainings", "form", "", "205", "training-icon");
        MenuCore.addMenuItem("2402", "Risk Değerlendirme", "/risk-assessment", "form", "", "205", "risk-icon");
        MenuCore.addMenuItem("2403", "İş Kazaları", "/work-accidents", "form", "", "205", "accident-icon");
        MenuCore.addMenuItem("2404", "Sağlık Kontrolleri", "/health-checks", "form", "", "205", "health-icon");
        MenuCore.addMenuItem("2405", "İSG Kurul Toplantıları", "/ohs-meetings", "form", "", "205", "meeting-icon");
        MenuCore.addMenuItem("2406", "Yasal Raporlar", "/legal-reports", "form", "", "205", "report-icon");
        MenuCore.addMenuItem("2407", "İSG Ekipmanları", "/ohs-equipment", "form", "", "205", "equipment-icon");

// FİNANS & MUHASEBE ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("301", "Genel Muhasebe", "3");
        MenuCore.addMenuItem("302", "Cari & Banka", "3");
        MenuCore.addMenuItem("303", "Fatura & E-Dönüşüm", "3");
        MenuCore.addMenuItem("304", "Sabit Kıymet & Bütçe", "3");
        MenuCore.addMenuItem("305", "Finansal Analiz", "3");

// GENEL MUHASEBE ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("3001", "Muhasebe Fişleri", "/accounting-vouchers", "form", "", "301", "voucher-icon");
        MenuCore.addMenuItem("3002", "Hesap Planı", "/chart-of-accounts", "form", "", "301", "account-icon");
        MenuCore.addMenuItem("3003", "Yevmiye Defteri", "/journal-book", "form", "", "301", "journal-icon");
        MenuCore.addMenuItem("3004", "Büyük Defter", "/general-ledger", "form", "", "301", "ledger-icon");
        MenuCore.addMenuItem("3005", "Mizan", "/trial-balance", "form", "", "301", "balance-icon");
        MenuCore.addMenuItem("3006", "Maliyet Muhasebesi", "/cost-accounting", "form", "", "301", "cost-icon");
        MenuCore.addMenuItem("3007", "Dönem Sonu İşlemleri", "/end-of-period", "form", "", "301", "end-icon");

// CARİ & BANKA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("3101", "Cari Hesaplar", "/accounts-receivable", "form", "", "302", "receivable-icon");
        MenuCore.addMenuItem("3102", "Cari Hareketler", "/account-movements", "form", "", "302", "movement-icon");
        MenuCore.addMenuItem("3103", "Banka Hesapları", "/bank-accounts", "form", "", "302", "/icons/16/grid.png");
        MenuCore.addMenuItem("3104", "Banka İşlemleri", "/bank-transactions", "form", "", "302", "transaction-icon");
        MenuCore.addMenuItem("3105", "Çek/Senet", "/cheques-bonds", "form", "", "302", "cheque-icon");
        MenuCore.addMenuItem("3106", "Kasa İşlemleri", "/cash-transactions", "form", "", "302", "cash-icon");
        MenuCore.addMenuItem("3107", "Kredi Takibi", "/credit-tracking", "form", "", "302", "credit-icon");

// FATURA & E-DÖNÜŞÜM ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("3201", "Satış Faturaları", "/sales-invoices", "form", "", "303", "invoice-icon");
        MenuCore.addMenuItem("3202", "Alış Faturaları", "/purchase-invoices", "form", "", "303", "purchase-icon");
        MenuCore.addMenuItem("3203", "E-Fatura", "/e-invoice", "form", "", "303", "e-invoice-icon");
        MenuCore.addMenuItem("3204", "E-Arşiv", "/e-archive", "form", "", "303", "archive-icon");
        MenuCore.addMenuItem("3205", "E-İrsaliye", "/e-dispatch", "form", "", "303", "dispatch-icon");
        MenuCore.addMenuItem("3206", "E-Defter", "/e-ledger", "form", "", "303", "e-ledger-icon");
        MenuCore.addMenuItem("3207", "E-Beyanname", "/e-declaration", "form", "", "303", "declaration-icon");

// SABİT KIYMET & BÜTÇE ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("3301", "Sabit Kıymet Kayıtları", "/fixed-asset-records", "form", "", "304", "asset-icon");
        MenuCore.addMenuItem("3302", "Amortisman Hesaplama", "/depreciation", "form", "", "304", "depreciation-icon");
        MenuCore.addMenuItem("3303", "Sabit Kıymet Satış", "/asset-sales", "form", "", "304", "sales-icon");
        MenuCore.addMenuItem("3304", "Bütçe Tanımları", "/budget-definitions", "form", "", "304", "budget-icon");
        MenuCore.addMenuItem("3305", "Bütçe Planlama", "/budget-planning", "form", "", "304", "planning-icon");
        MenuCore.addMenuItem("3306", "Bütçe Gerçekleşme", "/budget-realization", "form", "", "304", "realization-icon");
        MenuCore.addMenuItem("3307", "Bütçe Revizyonları", "/budget-revisions", "form", "", "304", "revision-icon");

// FİNANSAL ANALİZ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("3401", "Bilanço", "/balance-sheet", "form", "", "305", "balance-icon");
        MenuCore.addMenuItem("3402", "Gelir Tablosu", "/income-statement", "form", "", "305", "income-icon");
        MenuCore.addMenuItem("3403", "Nakit Akışı", "/cash-flow", "form", "", "305", "flow-icon");
        MenuCore.addMenuItem("3404", "Finansal Oranlar", "/financial-ratios", "form", "", "305", "ratio-icon");
        MenuCore.addMenuItem("3405", "Kar Merkezi Analizi", "/profit-center", "form", "", "305", "profit-icon");
        MenuCore.addMenuItem("3406", "Konsolidasyon", "/consolidation", "form", "", "305", "consolidation-icon");
        MenuCore.addMenuItem("3407", "Döviz Pozisyonu", "/forex-position", "form", "", "305", "forex-icon");

// SATIŞ & CRM ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("401", "Müşteri Yönetimi", "4");
        MenuCore.addMenuItem("402", "Satış Fırsatları", "4");
        MenuCore.addMenuItem("403", "Teklif & Sipariş", "4");
        MenuCore.addMenuItem("404", "Satış Analizi", "4");
        MenuCore.addMenuItem("405", "Müşteri Hizmetleri", "4");

// MÜŞTERİ YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("4001", "Müşteri Kartları", "/customer-cards", "form", "", "401", "customer-icon");
        MenuCore.addMenuItem("4002", "Müşteri İletişimi", "/customer-communication", "form", "", "401", "communication-icon");
        MenuCore.addMenuItem("4003", "Müşteri Segmentasyonu", "/customer-segmentation", "form", "", "401", "segmentation-icon");
        MenuCore.addMenuItem("4004", "Müşteri Ziyaretleri", "/customer-visits", "form", "", "401", "visit-icon");
        MenuCore.addMenuItem("4005", "Müşteri Aktiviteleri", "/customer-activities", "form", "", "401", "activity-icon");
        MenuCore.addMenuItem("4006", "Müşteri İlişkileri", "/customer-relations", "form", "", "401", "relation-icon");
        MenuCore.addMenuItem("4007", "Müşteri Temsilcileri", "/account-managers", "form", "", "401", "manager-icon");

// Diğer alt modül tanımları buraya eklenecektir...

// SATINALMA ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("501", "Tedarikçi Yönetimi", "5");
        MenuCore.addMenuItem("502", "Satın Alma Talepleri", "5");
        MenuCore.addMenuItem("503", "Teklif & Sipariş", "5");
        MenuCore.addMenuItem("504", "Tedarik & Kalite", "5");
        MenuCore.addMenuItem("505", "Tedarikçi Portali", "5");

// TEDARİKÇİ YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("5001", "Tedarikçi Kartları", "/supplier-cards", "form", "", "501", "supplier-icon");
        MenuCore.addMenuItem("5002", "Tedarikçi Değerlendirme", "/supplier-evaluation", "form", "", "501", "evaluation-icon");
        MenuCore.addMenuItem("5003", "Tedarikçi Kategorileri", "/supplier-categories", "form", "", "501", "category-icon");
        MenuCore.addMenuItem("5004", "Sözleşme Yönetimi", "/contract-management", "form", "", "501", "contract-icon");
        MenuCore.addMenuItem("5005", "Tedarikçi İletişim", "/supplier-communication", "form", "", "501", "communication-icon");
        MenuCore.addMenuItem("5006", "Tedarikçi Risk Yönetimi", "/supplier-risk", "form", "", "501", "risk-icon");
        MenuCore.addMenuItem("5007", "Tedarikçi Onboarding", "/supplier-onboarding", "form", "", "501", "onboarding-icon");

// SATIN ALMA TALEPLERİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("5101", "Talep Oluşturma", "/purchase-requisition", "form", "", "502", "requisition-icon");
        MenuCore.addMenuItem("5102", "Talep Onayları", "/requisition-approvals", "form", "", "502", "approval-icon");
        MenuCore.addMenuItem("5103", "Talep Takibi", "/requisition-tracking", "form", "", "502", "tracking-icon");
        MenuCore.addMenuItem("5104", "Toplu Talep Yönetimi", "/bulk-requisitions", "form", "", "502", "bulk-icon");
        MenuCore.addMenuItem("5105", "Acil Talepler", "/urgent-requisitions", "form", "", "502", "urgent-icon");
        MenuCore.addMenuItem("5106", "Bütçe Kontrollü Talepler", "/budget-controlled-req", "form", "", "502", "budget-icon");
        MenuCore.addMenuItem("5107", "Talep Şablonları", "/requisition-templates", "form", "", "502", "template-icon");

// TEKLİF & SİPARİŞ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("5201", "Teklif İstekleri", "/request-for-quotation", "form", "", "503", "rfq-icon");
        MenuCore.addMenuItem("5202", "Teklif Karşılaştırma", "/quote-comparison", "form", "", "503", "comparison-icon");
        MenuCore.addMenuItem("5203", "Teklif Onaylama", "/quote-approval", "form", "", "503", "approve-icon");
        MenuCore.addMenuItem("5204", "Satın Alma Siparişleri", "/purchase-orders", "form", "", "503", "order-icon");
        MenuCore.addMenuItem("5205", "Sipariş Takibi", "/order-tracking", "form", "", "503", "track-icon");
        MenuCore.addMenuItem("5206", "Satın Alma Onayları", "/purchase-approvals", "form", "", "503", "approval-icon");
        MenuCore.addMenuItem("5207", "Sipariş Revizyonları", "/order-revisions", "form", "", "503", "revision-icon");

// TEDARİK & KALİTE ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("5301", "Mal Kabul", "/goods-receipt", "form", "", "504", "receipt-icon");
        MenuCore.addMenuItem("5302", "Kalite Kontrol", "/quality-control", "form", "", "504", "quality-icon");
        MenuCore.addMenuItem("5303", "İade İşlemleri", "/return-management", "form", "", "504", "return-icon");
        MenuCore.addMenuItem("5304", "Tedarik Takibi", "/supply-tracking", "form", "", "504", "tracking-icon");
        MenuCore.addMenuItem("5305", "Teslimat Performansı", "/delivery-performance", "form", "", "504", "performance-icon");
        MenuCore.addMenuItem("5306", "Kalite Raporları", "/quality-reports", "form", "", "504", "report-icon");
        MenuCore.addMenuItem("5307", "Uygunsuzluk Yönetimi", "/non-conformance", "form", "", "504", "non-conformance-icon");

// TEDARİKÇİ PORTALİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("5401", "Portal Erişim Yönetimi", "/portal-access", "form", "", "505", "access-icon");
        MenuCore.addMenuItem("5402", "Teklif Yönetimi", "/quote-management", "form", "", "505", "quote-icon");
        MenuCore.addMenuItem("5403", "Sipariş Takibi", "/order-tracking-portal", "form", "", "505", "track-icon");
        MenuCore.addMenuItem("5404", "Fatura Yönetimi", "/invoice-management", "form", "", "505", "invoice-icon");
        MenuCore.addMenuItem("5405", "Doküman Paylaşımı", "/document-sharing", "form", "", "505", "document-icon");
        MenuCore.addMenuItem("5406", "Performans İzleme", "/performance-monitoring", "form", "", "505", "monitor-icon");
        MenuCore.addMenuItem("5407", "İletişim Merkezi", "/communication-center", "form", "", "505", "communication-icon");

// STOK & ENVANTER ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("601", "Ürün Yönetimi", "6");
        MenuCore.addMenuItem("602", "Depo & Lokasyon", "6");
        MenuCore.addMenuItem("603", "Stok Hareketleri", "6");
        MenuCore.addMenuItem("604", "Stok Takibi", "6");
        MenuCore.addMenuItem("605", "Envanter Yönetimi", "6");

// ÜRÜN YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("6001", "Ürün Kartları", "/product-cards", "form", "", "601", "product-icon");
        MenuCore.addMenuItem("6002", "Ürün Kategorileri", "/product-categories", "form", "", "601", "category-icon");
        MenuCore.addMenuItem("6003", "Ürün Bileşenleri", "/product-components", "form", "", "601", "component-icon");
        MenuCore.addMenuItem("6004", "Barkod Yönetimi", "/barcode-management", "form", "", "601", "barcode-icon");
        MenuCore.addMenuItem("6005", "Ölçü Birimleri", "/units-of-measure", "form", "", "601", "/icons/16/grid.png");
        MenuCore.addMenuItem("6006", "Ürün Özellikleri", "/product-attributes", "form", "", "601", "attribute-icon");
        MenuCore.addMenuItem("6007", "Varyant Yönetimi", "/variant-management", "form", "", "601", "variant-icon");

// DEPO & LOKASYON ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("6101", "Depo Tanımları", "/warehouse-definitions", "form", "", "602", "warehouse-icon");
        MenuCore.addMenuItem("6102", "Lokasyon Tanımları", "/location-definitions", "form", "", "602", "location-icon");
        MenuCore.addMenuItem("6103", "Raf Yönetimi", "/shelf-management", "form", "", "602", "shelf-icon");
        MenuCore.addMenuItem("6104", "Depo Yerleşim Planı", "/warehouse-layout", "form", "", "602", "layout-icon");
        MenuCore.addMenuItem("6105", "Depo Transfer İşlemleri", "/warehouse-transfers", "form", "", "602", "transfer-icon");
        MenuCore.addMenuItem("6106", "Depo Kullanıcıları", "/warehouse-users", "form", "", "602", "users-icon");
        MenuCore.addMenuItem("6107", "Alan Optimizasyonu", "/space-optimization", "form", "", "602", "optimization-icon");

// STOK HAREKETLERİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("6201", "Giriş İşlemleri", "/stock-entries", "form", "", "603", "entry-icon");
        MenuCore.addMenuItem("6202", "Çıkış İşlemleri", "/stock-issues", "form", "", "603", "issue-icon");
        MenuCore.addMenuItem("6203", "Transfer İşlemleri", "/stock-transfers", "form", "", "603", "transfer-icon");
        MenuCore.addMenuItem("6204", "İade İşlemleri", "/stock-returns", "form", "", "603", "return-icon");
        MenuCore.addMenuItem("6205", "Rezervasyon İşlemleri", "/stock-reservations", "form", "", "603", "reservation-icon");
        MenuCore.addMenuItem("6206", "Seri/Lot Takibi", "/serial-lot-tracking", "form", "", "603", "tracking-icon");
        MenuCore.addMenuItem("6207", "Hareket Raporları", "/movement-reports", "form", "", "603", "report-icon");

// STOK TAKİBİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("6301", "Stok Bakiye Raporu", "/stock-balance", "form", "", "604", "balance-icon");
        MenuCore.addMenuItem("6302", "Kritik Stok Seviyesi", "/critical-stock", "form", "", "604", "critical-icon");
        MenuCore.addMenuItem("6303", "Stok Yaşlandırma", "/stock-aging", "form", "", "604", "aging-icon");
        MenuCore.addMenuItem("6304", "Stok Değerleme", "/stock-valuation", "form", "", "604", "valuation-icon");
        MenuCore.addMenuItem("6305", "Malzeme İhtiyaç Planı", "/material-requirement", "form", "", "604", "requirement-icon");
        MenuCore.addMenuItem("6306", "ABC Analizi", "/abc-analysis", "form", "", "604", "abc-icon");
        MenuCore.addMenuItem("6307", "Stok Devir Hızı", "/inventory-turnover", "form", "", "604", "turnover-icon");

// ENVANTER YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("6401", "Sayım Fişleri", "/count-vouchers", "form", "", "605", "count-icon");
        MenuCore.addMenuItem("6402", "Sayım Planlaması", "/count-planning", "form", "", "605", "planning-icon");
        MenuCore.addMenuItem("6403", "Sayım Sonuçları", "/count-results", "form", "", "605", "results-icon");
        MenuCore.addMenuItem("6404", "Envanter Farkları", "/inventory-differences", "form", "", "605", "difference-icon");
        MenuCore.addMenuItem("6405", "Periyodik Sayım", "/periodic-counting", "form", "", "605", "periodic-icon");
        MenuCore.addMenuItem("6406", "Mobil Sayım İşlemleri", "/mobile-counting", "form", "", "605", "mobile-icon");
        MenuCore.addMenuItem("6407", "Sayım Onayları", "/count-approvals", "form", "", "605", "approval-icon");

// ÜRETİM ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("701", "Üretim Planlama", "7");
        MenuCore.addMenuItem("702", "Üretim Emirleri", "7");
        MenuCore.addMenuItem("703", "Üretim Takibi", "7");
        MenuCore.addMenuItem("704", "Üretim Maliyeti", "7");
        MenuCore.addMenuItem("705", "Üretim Kaynakları", "7");

// ÜRETİM PLANLAMA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("7001", "Ana Üretim Planı", "/master-production-plan", "form", "", "701", "master-icon");
        MenuCore.addMenuItem("7002", "Üretim Takvimi", "/production-calendar", "form", "", "701", "calendar-icon");
        MenuCore.addMenuItem("7003", "Kapasite Planlama", "/capacity-planning", "form", "", "701", "capa/icons/16/grid.png");
        MenuCore.addMenuItem("7004", "Malzeme İhtiyaç Planlaması", "/mrp", "form", "", "701", "mrp-icon");
        MenuCore.addMenuItem("7005", "Üretim Simülasyonu", "/production-simulation", "form", "", "701", "simulation-icon");
        MenuCore.addMenuItem("7006", "Çizelgeleme", "/scheduling", "form", "", "701", "schedule-icon");
        MenuCore.addMenuItem("7007", "Talep Tahminleri", "/demand-forecasting", "form", "", "701", "forecast-icon");

// ÜRETİM EMİRLERİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("7101", "Emir Oluşturma", "/create-order", "form", "", "702", "create-icon");
        MenuCore.addMenuItem("7102", "Emir Onaylama", "/approve-order", "form", "", "702", "approve-icon");
        MenuCore.addMenuItem("7103", "Emir Planlama", "/plan-order", "form", "", "702", "plan-icon");
        MenuCore.addMenuItem("7104", "Emir Takibi", "/track-order", "form", "", "702", "track-icon");
        MenuCore.addMenuItem("7105", "Emir Kapama", "/close-order", "form", "", "702", "close-icon");
        MenuCore.addMenuItem("7106", "Üretim Reçeteleri", "/bill-of-materials", "form", "", "702", "bom-icon");
        MenuCore.addMenuItem("7107", "Üretim Rotaları", "/production-routes", "form", "", "702", "route-icon");

// ÜRETİM TAKİBİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("7201", "Operasyon Takibi", "/operation-tracking", "form", "", "703", "operation-icon");
        MenuCore.addMenuItem("7202", "İş Emri Takibi", "/work-order-tracking", "form", "", "703", "work-icon");
        MenuCore.addMenuItem("7203", "Üretim Duruşları", "/production-stops", "form", "", "703", "stop-icon");
        MenuCore.addMenuItem("7204", "Üretim İzlenebilirliği", "/production-traceability", "form", "", "703", "trace-icon");
        MenuCore.addMenuItem("7205", "Hat Performansı", "/line-performance", "form", "", "703", "performance-icon");
        MenuCore.addMenuItem("7206", "Operatör Performansı", "/operator-performance", "form", "", "703", "operator-icon");
        MenuCore.addMenuItem("7207", "Üretim Raporları", "/production-reports", "form", "", "703", "report-icon");

// ÜRETİM MALİYETİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("7301", "Maliyet Hesaplama", "/cost-calculation", "form", "", "704", "calculation-icon");
        MenuCore.addMenuItem("7302", "Maliyet Merkezleri", "/cost-centers", "form", "", "704", "center-icon");
        MenuCore.addMenuItem("7303", "Direkt Maliyetler", "/direct-costs", "form", "", "704", "direct-icon");
        MenuCore.addMenuItem("7304", "Endirekt Maliyetler", "/indirect-costs", "form", "", "704", "indirect-icon");
        MenuCore.addMenuItem("7305", "Standart Maliyetler", "/standard-costs", "form", "", "704", "standard-icon");
        MenuCore.addMenuItem("7306", "Fiili Maliyetler", "/actual-costs", "form", "", "704", "actual-icon");
        MenuCore.addMenuItem("7307", "Maliyet Analizi", "/cost-analysis", "form", "", "704", "analysis-icon");

// ÜRETİM KAYNAKLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("7401", "Makine Tanımları", "/machine-definitions", "form", "", "705", "machine-icon");
        MenuCore.addMenuItem("7402", "İş Merkezi Tanımları", "/work-center-definitions", "form", "", "705", "work-center-icon");
        MenuCore.addMenuItem("7403", "Vardiya Tanımları", "/shift-definitions", "form", "", "705", "shift-icon");
        MenuCore.addMenuItem("7404", "Üretim Personeli", "/production-personnel", "form", "", "705", "personnel-icon");
        MenuCore.addMenuItem("7405", "Makine Bakımları", "/machine-maintenance", "form", "", "705", "maintenance-icon");
        MenuCore.addMenuItem("7406", "Kaynak Planlama", "/resource-planning", "form", "", "705", "planning-icon");
        MenuCore.addMenuItem("7407", "Kaynak Kullanımı", "/resource-utilization", "form", "", "705", "utilization-icon");

// PROJE YÖNETİMİ ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("801", "Proje Tanımları", "8");
        MenuCore.addMenuItem("802", "Proje Planlama", "8");
        MenuCore.addMenuItem("803", "Proje Takibi", "8");
        MenuCore.addMenuItem("804", "Proje Kaynakları", "8");
        MenuCore.addMenuItem("805", "Proje Dokümantasyonu", "8");

// PROJE TANIMLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("8001", "Proje Kartları", "/project-cards", "form", "", "801", "project-icon");
        MenuCore.addMenuItem("8002", "Proje Tipleri", "/project-types", "form", "", "801", "type-icon");
        MenuCore.addMenuItem("8003", "Proje Şablonları", "/project-templates", "form", "", "801", "template-icon");
        MenuCore.addMenuItem("8004", "Proje Kategorileri", "/project-categories", "form", "", "801", "category-icon");
        MenuCore.addMenuItem("8005", "Proje Özellikleri", "/project-attributes", "form", "", "801", "attribute-icon");
        MenuCore.addMenuItem("8006", "Proje Statüleri", "/project-statuses", "form", "", "801", "status-icon");
        MenuCore.addMenuItem("8007", "Proje Portföyü", "/project-portfolio", "form", "", "801", "portfolio-icon");

// PROJE PLANLAMA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("8101", "Görev Planlama", "/task-planning", "form", "", "802", "task-icon");
        MenuCore.addMenuItem("8102", "Zaman Çizelgesi", "/timeline", "form", "", "802", "timeline-icon");
        MenuCore.addMenuItem("8103", "Gantt Şeması", "/gantt-chart", "form", "", "802", "gantt-icon");
        MenuCore.addMenuItem("8104", "Kilometre Taşları", "/milestones", "form", "", "802", "milestone-icon");
        MenuCore.addMenuItem("8105", "Kaynak Atama", "/resource-assignment", "form", "", "802", "assignment-icon");
        MenuCore.addMenuItem("8106", "Proje Takvimi", "/project-calendar", "form", "", "802", "calendar-icon");
        MenuCore.addMenuItem("8107", "Bağımlılık Yönetimi", "/dependency-management", "form", "", "802", "dependency-icon");

// PROJE TAKİBİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("8201", "Görev Takibi", "/task-tracking", "form", "", "803", "track-icon");
        MenuCore.addMenuItem("8202", "İlerleme Raporu", "/progress-report", "form", "", "803", "progress-icon");
        MenuCore.addMenuItem("8203", "Sorun Takibi", "/issue-tracking", "form", "", "803", "issue-icon");
        MenuCore.addMenuItem("8204", "Risk Yönetimi", "/risk-management", "form", "", "803", "risk-icon");
        MenuCore.addMenuItem("8205", "Zaman Kaydı", "/time-recording", "form", "", "803", "time-icon");
        MenuCore.addMenuItem("8206", "Proje Durumu", "/project-status", "form", "", "803", "status-icon");
        MenuCore.addMenuItem("8207", "Düzeltici Faaliyetler", "/corrective-actions", "form", "", "803", "corrective-icon");

// PROJE KAYNAKLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("8301", "Kaynak Havuzu", "/resource-pool", "form", "", "804", "pool-icon");
        MenuCore.addMenuItem("8302", "Kaynak Kapasitesi", "/resource-capacity", "form", "", "804", "capa/icons/16/grid.png");
        MenuCore.addMenuItem("8303", "Kaynak Kullanımı", "/resource-utilization", "form", "", "804", "utilization-icon");
        MenuCore.addMenuItem("8304", "Proje Bütçesi", "/project-budget", "form", "", "804", "budget-icon");
        MenuCore.addMenuItem("8305", "Maliyet Takibi", "/cost-tracking", "form", "", "804", "cost-icon");
        MenuCore.addMenuItem("8306", "Proje Giderleri", "/project-expenses", "form", "", "804", "expense-icon");
        MenuCore.addMenuItem("8307", "Kaynak Çizelgeleme", "/resource-scheduling", "form", "", "804", "scheduling-icon");

// PROJE DOKÜMANTASYONU ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("8401", "Proje Dokümanları", "/project-documents", "form", "", "805", "document-icon");
        MenuCore.addMenuItem("8402", "Sözleşme Yönetimi", "/contract-management", "form", "", "805", "contract-icon");
        MenuCore.addMenuItem("8403", "Toplantı Notları", "/meeting-notes", "form", "", "805", "notes-icon");
        MenuCore.addMenuItem("8404", "Proje İletişimi", "/project-communication", "form", "", "805", "communication-icon");
        MenuCore.addMenuItem("8405", "Değişiklik Talepleri", "/change-requests", "form", "", "805", "change-icon");
        MenuCore.addMenuItem("8406", "Onay Süreçleri", "/approval-processes", "form", "", "805", "approval-icon");
        MenuCore.addMenuItem("8407", "Proje Arşivi", "/project-archive", "form", "", "805", "archive-icon");

// KALİTE YÖNETİMİ ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("901", "Kalite Kontrol", "9");
        MenuCore.addMenuItem("902", "Kalite Güvence", "9");
        MenuCore.addMenuItem("903", "Kalite Planlama", "9");
        MenuCore.addMenuItem("904", "Ölçüm & Analiz", "9");
        MenuCore.addMenuItem("905", "Süreç İyileştirme", "9");

// KALİTE KONTROL ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("9001", "Kalite Kontrol Planları", "/quality-control-plans", "form", "", "901", "control-icon");
        MenuCore.addMenuItem("9002", "Kalite Kontrol Noktaları", "/quality-control-points", "form", "", "901", "point-icon");
        MenuCore.addMenuItem("9003", "Muayene Tanımları", "/inspection-definitions", "form", "", "901", "inspection-icon");
        MenuCore.addMenuItem("9004", "Kontrol Listeleri", "/checklists", "form", "", "901", "checklist-icon");
        MenuCore.addMenuItem("9005", "Numune Yönetimi", "/sample-management", "form", "", "901", "sample-icon");
        MenuCore.addMenuItem("9006", "Kabul-Red Kriterleri", "/acceptance-criteria", "form", "", "901", "criteria-icon");
        MenuCore.addMenuItem("9007", "Test Sonuçları", "/test-results", "form", "", "901", "result-icon");

// KALİTE GÜVENCE ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("9101", "Kalite Standartları", "/quality-standards", "form", "", "902", "standard-icon");
        MenuCore.addMenuItem("9102", "Denetim Planları", "/audit-plans", "form", "", "902", "audit-icon");
        MenuCore.addMenuItem("9103", "Denetim Sonuçları", "/audit-results", "form", "", "902", "results-icon");
        MenuCore.addMenuItem("9104", "Düzeltici Faaliyetler", "/corrective-actions", "form", "", "902", "corrective-icon");
        MenuCore.addMenuItem("9105", "Önleyici Faaliyetler", "/preventive-actions", "form", "", "902", "preventive-icon");
        MenuCore.addMenuItem("9106", "Uygunsuzluk Yönetimi", "/non-conformance", "form", "", "902", "non-conformance-icon");
        MenuCore.addMenuItem("9107", "Doküman Kontrol", "/document-control", "form", "", "902", "document-icon");

// KALİTE PLANLAMA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("9201", "Kalite Politikaları", "/quality-policies", "form", "", "903", "policy-icon");
        MenuCore.addMenuItem("9202", "Kalite Hedefleri", "/quality-objectives", "form", "", "903", "objective-icon");
        MenuCore.addMenuItem("9203", "Kalite El Kitabı", "/quality-manual", "form", "", "903", "manual-icon");
        MenuCore.addMenuItem("9204", "Kalite Prosedürleri", "/quality-procedures", "form", "", "903", "procedure-icon");
        MenuCore.addMenuItem("9205", "İş Talimatları", "/work-instructions", "form", "", "903", "instruction-icon");
        MenuCore.addMenuItem("9206", "Kalite Planları", "/quality-plans", "form", "", "903", "plan-icon");
        MenuCore.addMenuItem("9207", "Risk Değerlendirme", "/risk-assessment", "form", "", "903", "assessment-icon");

// ÖLÇÜM & ANALİZ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("9301", "Ölçüm Cihazları", "/measurement-devices", "form", "", "904", "device-icon");
        MenuCore.addMenuItem("9302", "Kalibrasyon", "/calibration", "form", "", "904", "calibration-icon");
        MenuCore.addMenuItem("9303", "İstatistiksel Analiz", "/statistical-analysis", "form", "", "904", "statistical-icon");
        MenuCore.addMenuItem("9304", "Veri Toplama", "/data-collection", "form", "", "904", "collection-icon");
        MenuCore.addMenuItem("9305", "İstatistiksel Proses Kontrol", "/spc", "form", "", "904", "spc-icon");
        MenuCore.addMenuItem("9306", "Ölçüm Sistemleri Analizi", "/measurement-systems", "form", "", "904", "system-icon");
        MenuCore.addMenuItem("9307", "Kalite Raporları", "/quality-reports", "form", "", "904", "report-icon");

// SÜREÇ İYİLEŞTİRME ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("9401", "Süreç Haritaları", "/process-maps", "form", "", "905", "map-icon");
        MenuCore.addMenuItem("9402", "Süreç Performansı", "/process-performance", "form", "", "905", "performance-icon");
        MenuCore.addMenuItem("9403", "İyileştirme Projeleri", "/improvement-projects", "form", "", "905", "project-icon");
        MenuCore.addMenuItem("9404", "Süreç Analizi", "/process-analysis", "form", "", "905", "analysis-icon");
        MenuCore.addMenuItem("9405", "Kök Neden Analizi", "/root-cause-analysis", "form", "", "905", "root-cause-icon");
        MenuCore.addMenuItem("9406", "Süreç Standartlaştırma", "/process-standardization", "form", "", "905", "standardization-icon");
        MenuCore.addMenuItem("9407", "Sürekli İyileştirme", "/continuous-improvement", "form", "", "905", "continuous-icon");

// RAPORLAMA & İŞ ZEKASI ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("1001", "Standart Raporlar", "10");
        MenuCore.addMenuItem("1002", "Özel Raporlar", "10");
        MenuCore.addMenuItem("1003", "Dashboard & KPI", "10");
        MenuCore.addMenuItem("1004", "Veri Analizi", "10");
        MenuCore.addMenuItem("1005", "İş Zekası Araçları", "10");

// STANDART RAPORLAR ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("10001", "Finansal Raporlar", "/financial-reports", "form", "", "1001", "financial-icon");
        MenuCore.addMenuItem("10002", "Satış Raporları", "/sales-reports", "form", "", "1001", "sales-icon");
        MenuCore.addMenuItem("10003", "Satın Alma Raporları", "/purchase-reports", "form", "", "1001", "purchase-icon");
        MenuCore.addMenuItem("10004", "Stok Raporları", "/inventory-reports", "form", "", "1001", "inventory-icon");
        MenuCore.addMenuItem("10005", "Üretim Raporları", "/production-reports", "form", "", "1001", "production-icon");
        MenuCore.addMenuItem("10006", "İK Raporları", "/hr-reports", "form", "", "1001", "hr-icon");
        MenuCore.addMenuItem("10007", "Proje Raporları", "/project-reports", "form", "", "1001", "project-icon");

// ÖZEL RAPORLAR ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("10101", "Rapor Tasarımcısı", "/report-designer", "form", "", "1002", "designer-icon");
        MenuCore.addMenuItem("10102", "Özel Sorgu Oluşturma", "/custom-query", "form", "", "1002", "query-icon");
        MenuCore.addMenuItem("10103", "Rapor Şablonları", "/report-templates", "form", "", "1002", "template-icon");
        MenuCore.addMenuItem("10104", "Parametre Tanımları", "/parameter-definitions", "form", "", "1002", "parameter-icon");
        MenuCore.addMenuItem("10105", "Koşullu Biçimlendirme", "/conditional-formatting", "form", "", "1002", "formatting-icon");
        MenuCore.addMenuItem("10106", "Rapor Zamanlayıcı", "/report-scheduler", "form", "", "1002", "scheduler-icon");
        MenuCore.addMenuItem("10107", "Rapor Arşivi", "/report-archive", "form", "", "1002", "archive-icon");

// DASHBOARD & KPI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("10201", "Dashboard Oluşturma", "/dashboard-creation", "form", "", "1003", "creation-icon");
        MenuCore.addMenuItem("10202", "Dashboard Kişiselleştirme", "/dashboard-customization", "form", "", "1003", "customization-icon");
        MenuCore.addMenuItem("10203", "KPI Tanımları", "/kpi-definitions", "form", "", "1003", "kpi-icon");
        MenuCore.addMenuItem("10204", "KPI Hedefleri", "/kpi-targets", "form", "", "1003", "target-icon");
        MenuCore.addMenuItem("10205", "Görsel Bileşenler", "/visual-components", "form", "", "1003", "visual-icon");
        MenuCore.addMenuItem("10206", "Veri Güncelleme", "/data-refresh", "form", "", "1003", "refresh-icon");
        MenuCore.addMenuItem("10207", "Dashboard Paylaşımı", "/dashboard-sharing", "form", "", "1003", "sharing-icon");

// VERİ ANALİZİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("10301", "Veri Keşfi", "/data-discovery", "form", "", "1004", "/icons/48/grid.png");
        MenuCore.addMenuItem("10302", "Pivot Tablolar", "/pivot-tables", "form", "", "1004", "pivot-icon");
        MenuCore.addMenuItem("10303", "Veri Görselleştirme", "/data-visualization", "form", "", "1004", "visualization-icon");
        MenuCore.addMenuItem("10304", "Trend Analizi", "/trend-analysis", "form", "", "1004", "trend-icon");
        MenuCore.addMenuItem("10305", "Tahminsel Analitik", "/predictive-analytics", "form", "", "1004", "predictive-icon");
        MenuCore.addMenuItem("10306", "Veri Karşılaştırma", "/data-comparison", "form", "", "1004", "comparison-icon");
        MenuCore.addMenuItem("10307", "Veri İhracatı", "/data-export", "form", "", "1004", "export-icon");

// İŞ ZEKASI ARAÇLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("10401", "Veri Ambarı", "/data-warehouse", "form", "", "1005", "warehouse-icon");
        MenuCore.addMenuItem("10402", "ETL İşlemleri", "/etl-operations", "form", "", "1005", "etl-icon");
        MenuCore.addMenuItem("10403", "Veri Modelleme", "/data-modeling", "form", "", "1005", "modeling-icon");
        MenuCore.addMenuItem("10404", "OLAP Küpleri", "/olap-cubes", "form", "", "1005", "olap-icon");
        MenuCore.addMenuItem("10405", "Veri Madenciliği", "/data-mining", "form", "", "1005", "mining-icon");
        MenuCore.addMenuItem("10406", "Sorgulama Araçları", "/query-tools", "form", "", "1005", "query-icon");
        MenuCore.addMenuItem("10407", "Entegrasyon Bağlantıları", "/integration-connections", "form", "", "1005", "connection-icon");

// LOJİSTİK & SEVKİYAT ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("1101", "Sevkiyat Planlama", "11");
        MenuCore.addMenuItem("1102", "Taşıma Yönetimi", "11");
        MenuCore.addMenuItem("1103", "Dağıtım Yönetimi", "11");
        MenuCore.addMenuItem("1104", "Teslimat İşlemleri", "11");
        MenuCore.addMenuItem("1105", "Lojistik Raporlama", "11");

// SEVKİYAT PLANLAMA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("11001", "Sevkiyat Planları", "/shipment-plans", "form", "", "1101", "plan-icon");
        MenuCore.addMenuItem("11002", "Rota Optimizasyonu", "/route-optimization", "form", "", "1101", "route-icon");
        MenuCore.addMenuItem("11003", "Yükleme Planlaması", "/loading-planning", "form", "", "1101", "loading-icon");
        MenuCore.addMenuItem("11004", "Kapasite Planlama", "/capacity-planning", "form", "", "1101", "capa/icons/16/grid.png");
        MenuCore.addMenuItem("11005", "Sevkiyat Takvimi", "/shipment-calendar", "form", "", "1101", "calendar-icon");
        MenuCore.addMenuItem("11006", "Sevkiyat Önceliklendirme", "/shipment-prioritization", "form", "", "1101", "priority-icon");
        MenuCore.addMenuItem("11007", "Konsolidasyon Planlama", "/consolidation-planning", "form", "", "1101", "consolidation-icon");

        // HİZMET SEVİYESİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("13401", "SLA Tanımları", "/sla-definitions", "form", "", "1305", "sla-icon");
        MenuCore.addMenuItem("13402", "Hizmet Katalogları", "/service-catalogs", "form", "", "1305", "catalog-icon");
        MenuCore.addMenuItem("13403", "Performans Ölçütleri", "/performance-metrics", "form", "", "1305", "metric-icon");
        MenuCore.addMenuItem("13404", "Müşteri Memnuniyeti", "/customer-satisfaction", "form", "", "1305", "satisfaction-icon");
        MenuCore.addMenuItem("13405", "Anketler", "/surveys", "form", "", "1305", "survey-icon");
        MenuCore.addMenuItem("13406", "Hizmet Raporları", "/service-reports", "form", "", "1305", "report-icon");
        MenuCore.addMenuItem("13407", "Servis Analitiği", "/service-analytics", "form", "", "1305", "analytics-icon");

// E-TİCARET & PAZARLAMA ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("1401", "E-Ticaret Yönetimi", "14");
        MenuCore.addMenuItem("1402", "Pazarlama Kampanyaları", "14");
        MenuCore.addMenuItem("1403", "Dijital Pazarlama", "14");
        MenuCore.addMenuItem("1404", "Müşteri Sadakati", "14");
        MenuCore.addMenuItem("1405", "Satış Kanalları", "14");

// E-TİCARET YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("14001", "Online Mağaza", "/online-store", "form", "", "1401", "store-icon");
        MenuCore.addMenuItem("14002", "Ürün Kataloğu", "/product-catalog", "form", "", "1401", "catalog-icon");
        MenuCore.addMenuItem("14003", "Sepet & Ödeme", "/cart-checkout", "form", "", "1401", "cart-icon");
        MenuCore.addMenuItem("14004", "Sipariş Yönetimi", "/order-management", "form", "", "1401", "order-icon");
        MenuCore.addMenuItem("14005", "Fiyatlandırma", "/pricing", "form", "", "1401", "pricing-icon");
        MenuCore.addMenuItem("14006", "Stok Entegrasyonu", "/inventory-integration", "form", "", "1401", "integration-icon");
        MenuCore.addMenuItem("14007", "E-Ticaret Raporları", "/ecommerce-reports", "form", "", "1401", "report-icon");

// PAZARLAMA KAMPANYALARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("14101", "Kampanya Yönetimi", "/campaign-management", "form", "", "1402", "campaign-icon");
        MenuCore.addMenuItem("14102", "İndirim & Promosyon", "/discount-promotion", "form", "", "1402", "promotion-icon");
        MenuCore.addMenuItem("14103", "Kampanya Takvimi", "/campaign-calendar", "form", "", "1402", "calendar-icon");
        MenuCore.addMenuItem("14104", "Hedef Kitle", "/target-audience", "form", "", "1402", "audience-icon");
        MenuCore.addMenuItem("14105", "Kampanya Bütçesi", "/campaign-budget", "form", "", "1402", "budget-icon");
        MenuCore.addMenuItem("14106", "Kampanya Performansı", "/campaign-performance", "form", "", "1402", "performance-icon");
        MenuCore.addMenuItem("14107", "A/B Testleri", "/ab-tests", "form", "", "1402", "test-icon");

// DİJİTAL PAZARLAMA ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("14201", "E-posta Pazarlama", "/email-marketing", "form", "", "1403", "email-icon");
        MenuCore.addMenuItem("14202", "SEO Yönetimi", "/seo-management", "form", "", "1403", "seo-icon");
        MenuCore.addMenuItem("14203", "Sosyal Medya", "/social-media", "form", "", "1403", "social-icon");
        MenuCore.addMenuItem("14204", "İçerik Yönetimi", "/content-management", "form", "", "1403", "content-icon");
        MenuCore.addMenuItem("14205", "Web Analitiği", "/web-analytics", "form", "", "1403", "analytics-icon");
        MenuCore.addMenuItem("14206", "Dijital Reklamlar", "/digital-ads", "form", "", "1403", "ads-icon");
        MenuCore.addMenuItem("14207", "Pazarlama Otomasyonu", "/marketing-automation", "form", "", "1403", "automation-icon");

// MÜŞTERİ SADAKATİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("14301", "Sadakat Programları", "/loyalty-programs", "form", "", "1404", "loyalty-icon");
        MenuCore.addMenuItem("14302", "Puan Yönetimi", "/point-management", "form", "", "1404", "point-icon");
        MenuCore.addMenuItem("14303", "Üyelik Yönetimi", "/membership-management", "form", "", "1404", "membership-icon");
        MenuCore.addMenuItem("14304", "Hediye Çeki & Kupon", "/gift-voucher-coupon", "form", "", "1404", "voucher-icon");
        MenuCore.addMenuItem("14305", "Müşteri Segmentasyonu", "/customer-segmentation", "form", "", "1404", "segmentation-icon");
        MenuCore.addMenuItem("14306", "Özel Teklifler", "/special-offers", "form", "", "1404", "offer-icon");
        MenuCore.addMenuItem("14307", "Sadakat Analitiği", "/loyalty-analytics", "form", "", "1404", "analytics-icon");

// SATIŞ KANALLARI ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("14401", "Kanal Yönetimi", "/channel-management", "form", "", "1405", "channel-icon");
        MenuCore.addMenuItem("14402", "Çoklu Kanal Entegrasyonu", "/multichannel-integration", "form", "", "1405", "integration-icon");
        MenuCore.addMenuItem("14403", "Pazaryeri Entegrasyonu", "/marketplace-integration", "form", "", "1405", "marketplace-icon");
        MenuCore.addMenuItem("14404", "POS Entegrasyonu", "/pos-integration", "form", "", "1405", "pos-icon");
        MenuCore.addMenuItem("14405", "Mobil Uygulamalar", "/mobile-apps", "form", "", "1405", "mobile-icon");
        MenuCore.addMenuItem("14406", "B2B & B2C Kanallar", "/b2b-b2c-channels", "form", "", "1405", "b2b-icon");
        MenuCore.addMenuItem("14407", "Kanal Performansı", "/channel-performance", "form", "", "1405", "performance-icon");

// DOKÜMAN & YASAL UYUM ALT MODÜLLERİ (3 parametreli)
        MenuCore.addMenuItem("1501", "Doküman Yönetimi", "15");
        MenuCore.addMenuItem("1502", "Yasal Uyum", "15");
        MenuCore.addMenuItem("1503", "Sözleşme Yönetimi", "15");
        MenuCore.addMenuItem("1504", "İş Süreci Yönetimi", "15");
        MenuCore.addMenuItem("1505", "Veri Güvenliği", "15");

// DOKÜMAN YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("15001", "Doküman Arşivi", "/document-archive", "form", "", "1501", "archive-icon");
        MenuCore.addMenuItem("15002", "Doküman Kategorileri", "/document-categories", "form", "", "1501", "category-icon");
        MenuCore.addMenuItem("15003", "Doküman Şablonları", "/document-templates", "form", "", "1501", "template-icon");
        MenuCore.addMenuItem("15004", "Versiyon Kontrolü", "/version-control", "form", "", "1501", "version-icon");
        MenuCore.addMenuItem("15005", "Doküman Tarama", "/document-scanning", "form", "", "1501", "scanning-icon");
        MenuCore.addMenuItem("15006", "OCR İşlemleri", "/ocr-operations", "form", "", "1501", "ocr-icon");
        MenuCore.addMenuItem("15007", "Doküman Paylaşımı", "/document-sharing", "form", "", "1501", "sharing-icon");

// YASAL UYUM ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("15101", "Mevzuat Takibi", "/legislation-tracking", "form", "", "1502", "legislation-icon");
        MenuCore.addMenuItem("15102", "Uyum Politikaları", "/compliance-policies", "form", "", "1502", "policy-icon");
        MenuCore.addMenuItem("15103", "KVKK Uyumu", "/gdpr-compliance", "form", "", "1502", "gdpr-icon");
        MenuCore.addMenuItem("15104", "Denetim Yönetimi", "/audit-management", "form", "", "1502", "audit-icon");
        MenuCore.addMenuItem("15105", "Risk Yönetimi", "/risk-management", "form", "", "1502", "risk-icon");
        MenuCore.addMenuItem("15106", "Yasal Raporlama", "/legal-reporting", "form", "", "1502", "reporting-icon");
        MenuCore.addMenuItem("15107", "Veri Koruma", "/data-protection", "form", "", "1502", "protection-icon");

// SÖZLEŞME YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("15201", "Sözleşme Oluşturma", "/contract-creation", "form", "", "1503", "creation-icon");
        MenuCore.addMenuItem("15202", "Sözleşme Şablonları", "/contract-templates", "form", "", "1503", "template-icon");
        MenuCore.addMenuItem("15203", "Sözleşme Onayları", "/contract-approvals", "form", "", "1503", "approval-icon");
        MenuCore.addMenuItem("15204", "Sözleşme Takibi", "/contract-tracking", "form", "", "1503", "tracking-icon");
        MenuCore.addMenuItem("15205", "Yenileme Bildirimleri", "/renewal-notifications", "form", "", "1503", "notification-icon");
        MenuCore.addMenuItem("15206", "Sözleşme Arşivi", "/contract-archive", "form", "", "1503", "archive-icon");
        MenuCore.addMenuItem("15207", "E-İmza Entegrasyonu", "/e-signature-integration", "form", "", "1503", "signature-icon");

// İŞ SÜRECİ YÖNETİMİ ALT MENÜLERİ (çok parametreli)
        MenuCore.addMenuItem("15301", "Süreç Tasarımı", "/process-design", "form", "", "1504", "design-icon");
        MenuCore.addMenuItem("15302", "İş Akışları", "/workflows", "form", "", "1504", "workflow-icon");
        MenuCore.addMenuItem("15303", "Süreç Otomasyonu", "/process-automation", "form", "", "1504", "automation-icon");
        MenuCore.addMenuItem("15304", "Onay Mekanizmaları", "/approval-mechanisms", "form", "", "1504", "mechanism-icon");
        MenuCore.addMenuItem("15305", "Süreç İzleme", "/process-monitoring", "form", "", "1504", "monitoring-icon");
        MenuCore.addMenuItem("15306", "Süreç Performansı", "/process-performance", "form", "", "1504", "performance-icon");
        MenuCore.addMenuItem("15307", "Süreç İyileştirme", "/process-improvement", "form", "", "1504", "improvement-icon");

    }

    // Menü öğelerini tutan liste
    private static final List<MenuItem> menuItems = new ArrayList<>();

    /**
     * Menü öğesi ekler
     */
    public static MenuItem addMenuItem(String id, String title) {
        MenuItem item = new MenuItem(id, title);
        menuItems.add(item);
        return item;
    }

    /**
     * Menü öğesi ekler
     */
    public static MenuItem addMenuItem(String id, String title, String icon, String parentId) {
        MenuItem item = new MenuItem(id, title,icon,parentId);
        menuItems.add(item);
        return item;
    }

    /**
     * Menü öğesi ekler parentId ile birlikte
     */
    public static MenuItem addMenuItem(String id, String title, String parentId) {
        MenuItem item = new MenuItem(id, title,parentId);
        menuItems.add(item);
        return item;
    }

    /**
     * Menü öğesi ekler (tüm parametrelerle)
     */
    public static MenuItem addMenuItem(String id, String title, String service, String action,
                                       String params, String parentId, String icon) {
        MenuItem item = new MenuItem(id, title, service, action, params, parentId, icon);
        menuItems.add(item);
        return item;
    }

    /**
     * ID'ye göre menü öğesini bulur
     */
    public static MenuItem findMenuItemById(String id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Menü öğesini siler
     */
    public static void removeMenuItem(String id) {
        menuItems.removeIf(item -> item.getId().equals(id));
    }

    /**
     * Tüm menü öğelerini döndürür
     */
    public static List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems);
    }

    /**
     * Hiyerarşik menü yapısını oluşturur
     */
    public static List<MenuItemStructure> getMenuStructure() {
        List<MenuItemStructure> result = new ArrayList<>();
        Map<String, MenuItemStructure> itemMap = new HashMap<>();

        // Önce tüm MenuItemStructure nesnelerini oluştur
        for (MenuItem item : menuItems) {
            MenuItemStructure itemStructure = new MenuItemStructure(item);
            itemMap.put(item.getId(), itemStructure);
        }

        // Sonra parent-child ilişkisini kur
        for (MenuItem item : menuItems) {
            if (item.getParentId() != null && !item.getParentId().isEmpty()) {
                MenuItemStructure parent = itemMap.get(item.getParentId());
                if (parent != null) {
                    parent.getChildren().add(itemMap.get(item.getId()));
                } else {
                    // Parent yoksa, root menü olarak ekle
                    result.add(itemMap.get(item.getId()));
                }
            } else {
                // Parent ID boşsa, root menü olarak ekle
                result.add(itemMap.get(item.getId()));
            }
        }

        return result;
    }

    /**
     * Menü bileşeni sınıfı
     */
    public static class MenuItem {
        private String id;
        private String title;
        private String service;
        private String action;
        private String params;
        private String parentId;
        private String icon = "/icons/48/grid.png";

        public MenuItem(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public MenuItem(String id, String title, String icon, String parentId) {
            this.id = id;
            this.title = title;
            this.icon = icon;
            this.parentId = parentId;
        }

        public MenuItem(String id, String title,String parentId) {
            this.id = id;
            this.title = title;
            this.parentId = parentId;
        }

        public MenuItem(String id, String title, String service, String action,
                        String params, String parentId, String icon) {
            this.id = id;
            this.title = title;
            this.service = service;
            this.action = action;
            this.params = params;
            this.parentId = parentId;
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    /**
     * Hiyerarşik menü yapısı sınıfı
     */
    public static class MenuItemStructure {
        private String id;
        private String title;
        private String service;
        private String action;
        private String params;
        private String icon;
        private List<MenuItemStructure> children = new ArrayList<>();

        public MenuItemStructure(MenuItem item) {
            this.id = item.getId();
            this.title = item.getTitle();
            this.service = item.getService();
            this.action = item.getAction();
            this.params = item.getParams();
            this.icon = item.getIcon();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<MenuItemStructure> getChildren() {
            return children;
        }

        public void setChildren(List<MenuItemStructure> children) {
            this.children = children;
        }
    }
}