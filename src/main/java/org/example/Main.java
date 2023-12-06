package org.example;

import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Yemek> menu = new ArrayList<>();
        menu.add(new Yemek("Borsh", BigDecimal.valueOf(3.0), 300, YemekKatagory.SUP));
        menu.add(new Yemek("Ash", BigDecimal.valueOf(4.0), 600, YemekKatagory.ET));
        menu.add(new Yemek("Kabab", BigDecimal.valueOf(3.0), 700, YemekKatagory.ET));
        menu.add(new Yemek("Badimcan salati", BigDecimal.valueOf(2.50), 400, YemekKatagory.TEREVEZ));
        menu.add(new Yemek("Toyq sous", BigDecimal.valueOf(3.0), 200, YemekKatagory.SUP));
        menu.add(new Yemek("Pizza", BigDecimal.valueOf(25.0), 1000, YemekKatagory.DIGER));
        menu.add(new Yemek("ayran", BigDecimal.valueOf(2.0), 100, YemekKatagory.ICKI));
        menu.add(new Yemek("yarpaq dolmasi", BigDecimal.valueOf(10), 600, YemekKatagory.ET));
        menu.add(new Yemek("Badimcan dolmasi", BigDecimal.valueOf(6.0), 1500, YemekKatagory.ET));
        menu.add(new Yemek("paytaxt salati", BigDecimal.valueOf(5.0), 1300, YemekKatagory.SUP));
        menu.add(new Yemek("gurcu xengeli", BigDecimal.valueOf(25.0), 1800, YemekKatagory.XEMIR));
        menu.add(new Yemek("paytaxt salati", BigDecimal.valueOf(5.0), 1300, YemekKatagory.SUP));

        menu.stream()
                .sorted(Comparator.comparing(Yemek::getQiymeti).reversed())
                .findFirst()
                .ifPresent(yemek -> System.out.println(yemek.getAdi() + " " + yemek.getQiymeti()));
        menu.stream()

                .max(Comparator.comparing(Yemek::getQiymeti))
                .ifPresent(yemek -> System.out.println(yemek.getAdi() + " " + yemek.getQiymeti()));

        menu.stream()
                .min(Comparator.comparing(Yemek::getQiymeti))
                .ifPresent(yemek -> System.out.println(yemek.getAdi() + " " + yemek.getQiymeti()));

        System.out.println("vegetarian yemekleri siyahisi:");
        menu.stream()
                .filter(yemek -> yemek.getKatagory().equals(YemekKatagory.TEREVEZ)
                        || yemek.getKatagory().equals(YemekKatagory.XEMIR))
                // .forEach(yemek -> System.out.println(yemek.getAdi()+" "+ yemek.getQiymeti()));
                .forEach(System.out::println);
        // menyudaki butun yemek kalori cemi:
        System.out.println("menyudaki butun yemek kalori cemi:");
       /* menu.stream()
                .map(yemek -> yemek.getKatagory())
               // .reduce(Integer:: sum)
             //   .ifPresent(System.out::println);
        */

// Dietik,normal, yagli yemeklerin siyahisi:
        Map<YemekKaloriSeviyyesi, List<Yemek>> qrup = menu.stream()
                .collect(Collectors.groupingBy(yemek -> {
                    if (yemek.getKalori() <= 300) {
                        return YemekKaloriSeviyyesi.DIYETIK;
                    } else if (yemek.getKalori() <= 1000) {
                        return YemekKaloriSeviyyesi.NORMAL;
                    } else {
                        return YemekKaloriSeviyyesi.YAGLI;
                    }
                }));
        qrup.forEach((kaloriSeviyyesi, yemekler) -> System.out.println(kaloriSeviyyesi + " " + yemekler));

// Java 7 ile eyni kodu yazaq
        System.out.println("Java 7 ile: ");
        Map<YemekKaloriSeviyyesi, List<Yemek>> map = new HashMap<>();
        for (Yemek yemek : menu) {
            YemekKaloriSeviyyesi kaloriSeviyyesi = null;
            if (yemek.getKalori() <= 300) {
                kaloriSeviyyesi = YemekKaloriSeviyyesi.DIYETIK;
            } else if (yemek.getKalori() <= 1000) {
                kaloriSeviyyesi = YemekKaloriSeviyyesi.NORMAL;
            } else {
                kaloriSeviyyesi = YemekKaloriSeviyyesi.YAGLI;
            }

            if (map.containsKey(kaloriSeviyyesi)) {
                List<Yemek> yemeks = map.get(kaloriSeviyyesi);
                yemeks.add(yemek);
                map.put(kaloriSeviyyesi, yemeks);
            } else {
                List<Yemek> yemeks = new ArrayList<>();
                yemeks.add(yemek);
                map.put(kaloriSeviyyesi, yemeks);
            }
        }

        for (Map.Entry<YemekKaloriSeviyyesi, List<Yemek>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().stream().map(Yemek::getAdi).collect(Collectors.joining(",")));
        }

    }
}