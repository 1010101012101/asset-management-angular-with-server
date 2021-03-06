package de.spqrinfo.assetmanagement.service;

import de.spqrinfo.assetmanagement.persistence.Asset;
import de.spqrinfo.assetmanagement.persistence.AssetType;
import de.spqrinfo.assetmanagement.persistence.UploadFile;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Felix on 26.02.2015.
 *
 * nicht fertig!!
 */

@Stateless
public class AssetManagementService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<AssetType> getAssetTypes(){
        final TypedQuery<AssetType> query = this.em.createNamedQuery("AssetType.findAll", AssetType.class);

        return query.getResultList();
    }

    // für suche hier noch was machen

    @Transactional
    public long getAssetTypeCount(){
        return (long) this.em.createNamedQuery("AssetType.count").getSingleResult();
    }

    @Transactional
    public AssetType createAssetType(final AssetType assetType){
        this.em.persist(assetType);
        return assetType;
    }

    @Transactional
    public AssetType updateAssetType(final AssetType assetType){
        this.em.persist(assetType);
        return assetType;
    }

    @Transactional
    public AssetType getAssetType(final long typeId){
        final AssetType assetType= this.em.find(AssetType.class, typeId);
        return assetType;
    }

    @Transactional
    public void deleteAssetType(final long id){
        final AssetType assetType= getAssetType(id);
        this.em.remove(assetType);
    }



    @Transactional
    public void createAsset(final AssetType assetType, final Asset asset) {
        asset.setAssetID(null);
        asset.setAssetType(assetType);

        this.em.persist(asset);
    }

    @Transactional
    public List<Asset> getAssets(Integer pageSize, Integer page) {
        final TypedQuery<Asset> query= this.em.createNamedQuery("Asset.findAll",Asset.class);

        if(pageSize != null && page!=null){
            query.setFirstResult((page - 1)*pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }



    public Asset getAsset(long id) {
        return this.em.find(Asset.class,id);

    }

}
