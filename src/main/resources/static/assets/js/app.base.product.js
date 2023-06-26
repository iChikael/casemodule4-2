class AppBase {
    static DOMAIN_SERVER = location.origin
    static API_SERVER = this.DOMAIN_SERVER + '/api'

    static API_PRODUCT = this.API_SERVER + '/products'
    static IMAGE_SCALE_W_80_h_60_Q_90 = 'c_scale,w_80,h_60,q_90'
    static DOMAIN_CLOUDINARY = 'https://res.cloudinary.com/toanphat/image/upload'
    // static API_LOCATION_REGION = 'https://vapi.vnappmob.com/api/province'



}
class Product {
    constructor(id, nameProduct, price, quantity, category, unit, description, productAvatar) {
        this.id = id;
        this.title = nameProduct;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.unit = unit;
        this.description = description;
        this.productAvatar = productAvatar;
    }
}

class ProductAvatar {
    constructor(id, fileName, fileFolder, fileUrl, fileType, cloudId, ts) {
        this.id = id;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.cloudId = cloudId;
        this.ts = ts;
    }
}

class Category {
    constructor(id,title) {
        this.id = id;
        this.title = title;
    }
}

class Unit {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }

}