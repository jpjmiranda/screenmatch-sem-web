package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private  Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "http://www.omdbapi.com/?t=" ;
    private final  String API_KEY = "&apikey=dec142f8";
    private final String SEASON = "&season=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    public void exibeMenu(){
        System.out.println("Digite o nome da série: ");
        var nomeSerie = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json,DadosSerie.class);
        //http://www.omdbapi.com/?t=gilmore+girls&apikey=dec142f8
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1 ;i<= dados.totalTemporadas();i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + SEASON + i +  API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json,DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out :: println );

        temporadas.forEach(t -> t.episodios().forEach(e-> System.out.println(e.titulo())));
    }
}

