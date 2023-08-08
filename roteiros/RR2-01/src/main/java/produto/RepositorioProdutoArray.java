package produto;

public class RepositorioProdutoArray implements RepositorioI<Produto>{
    private Produto[] produtos;
    private int index = -1;

    public RepositorioProdutoArray(int size){
        this.produtos = new Produto[size];
    }

    
    private int procuraIndice(int codigo) {
        int out = -1;

        for(int i = 0; i < produtos.length; i++){
            if(produtos[i].getCodigo() == codigo){
                out = 1;
            }
        }

        return out;
    }

    @Override
    public boolean existe(int codigo) {
       int i = this.procuraIndice(codigo);
       return i != -1;
    }


    @Override
    public void inserir(Produto produto) {
        index = index + 1;
        produtos[index] = produto;
    }

    @Override
    public void atualizar(Produto produto) {
       int i = this.procuraIndice(produto.getCodigo());
       if(i != -1){
        produtos[i] = produto;
       } else {
        throw new RuntimeException("Produto não encontrado!");
       }
    }

    @Override
    public void remover(int codigo) {
        int i = this.procuraIndice(codigo);
        if(i != -1){
            produtos[i] = produtos[index];
            produtos[index] = null;
            index = index-1;
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }

    @Override
    public Produto procurar(int codigo) {
        int i = this.procuraIndice(codigo);
        if(i != -1){
            return produtos[i];
        } else {
            throw new RuntimeException("Produto não encontrado!");
        }
    }


}
