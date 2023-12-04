package com.example.capstone1db.Service;


import com.example.capstone1db.Model.Merchant;
import com.example.capstone1db.Model.MerchantStock;
import com.example.capstone1db.Model.Product;
import com.example.capstone1db.Model.User;
import com.example.capstone1db.Repository.MerchantRepository;
import com.example.capstone1db.Repository.MerchantStockRepository;
import com.example.capstone1db.Repository.ProductRepository;
import com.example.capstone1db.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    public List<MerchantStock> getMerchantStocks(){
        return merchantStockRepository.findAll();
    }
    public void addMerchantStock(MerchantStock merchantStock){
        merchantStockRepository.save(merchantStock);
    }
    public Boolean updateMerchantStock(Integer id,MerchantStock merchantStock){
        MerchantStock oldMerchantStock=merchantStockRepository.getById(id);
        if(oldMerchantStock==null){
            return false;
        }
        oldMerchantStock.setProductId(merchantStock.getProductId());
        oldMerchantStock.setMerchantId(merchantStock.getMerchantId());
        oldMerchantStock.setStock(merchantStock.getStock());

        merchantStockRepository.save(oldMerchantStock);
        return true;
    }
    public Boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock=merchantStockRepository.getById(id);
        if(merchantStock==null){
            return false;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }

    //endpoint
    public Integer addStock(Integer productId,Integer merchantStockId,Integer amount){
        MerchantStock odlMerchantStock=merchantStockRepository.getById(merchantStockId);
        if(merchantStockId==null){
            return 1;
        }
        Product productId2=productRepository.getById(productId);
        if(productId2==null){
            return 2;
        }
        odlMerchantStock.setStock(odlMerchantStock.getStock()+amount);
        odlMerchantStock.setProductId(odlMerchantStock.getProductId()+productId);
        merchantStockRepository.save(odlMerchantStock);
        return 0;
    }
}
