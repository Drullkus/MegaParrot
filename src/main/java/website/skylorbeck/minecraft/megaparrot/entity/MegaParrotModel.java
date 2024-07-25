package website.skylorbeck.minecraft.megaparrot.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import website.skylorbeck.minecraft.megaparrot.Declarar;

public class MegaParrotModel extends GeoModel<MegaParrotEntity> {
    @Override
    public Identifier getModelResource(MegaParrotEntity object) {
        return Declarar.getMegaParrotId("geo/mega_parrot.geo.json");
    }

    @Override
    public Identifier getTextureResource(MegaParrotEntity object) {
        if (object.getCustomName() != null) {
            String name = object.getName().getString();
            if (name.equalsIgnoreCase("mordecai")){
                return Declarar.getMegaParrotId("textures/entity/mordecai.png");
            } else
            if (name.equalsIgnoreCase("CommissarGrey")){
                return Declarar.getMegaParrotId("textures/entity/phoenix.png");
            } else
            if (name.equalsIgnoreCase("Chone")){
                return Declarar.getMegaParrotId("textures/entity/chone.png");
            } else
            if (name.equalsIgnoreCase("Striker")){
                return Declarar.getMegaParrotId("textures/entity/booby.png");
            }  else
            if (name.equalsIgnoreCase("Oregano")||name.equalsIgnoreCase("Parsley")){
                return Declarar.getMegaParrotId("textures/entity/rooster.png");
            } else
            if (name.equalsIgnoreCase("SkylorBeck")){
                return Declarar.getMegaParrotId("textures/entity/noise.png");
            } else
            if (name.equalsIgnoreCase("cattamale")){
                return Declarar.getMegaParrotId("textures/entity/cardinal.png");
            }
        }
        switch (object.getVariant()) {
            default -> {
                return Declarar.getMegaParrotId("textures/entity/red_parrot.png");
            }
            case 1 -> {
                return Declarar.getMegaParrotId("textures/entity/blue_parrot.png");
            }
            case 2 -> {
                return Declarar.getMegaParrotId("textures/entity/green_parrot.png");
            }
            case 3 -> {
                return Declarar.getMegaParrotId("textures/entity/cyan_parrot.png");
            }
            case 4 -> {
                return Declarar.getMegaParrotId("textures/entity/grey_parrot.png");
            }
            case 5 -> {
                return Declarar.getMegaParrotId("textures/entity/snow.png");
            }
            case 6 -> {
                return Declarar.getMegaParrotId("textures/entity/kulu.png");
            }
        }
    }

    @Override
    public Identifier getAnimationResource(MegaParrotEntity animatable) {
        return Declarar.getMegaParrotId("animations/mega_parrot.animation.json");
    }

    @Override
    public void setCustomAnimations(MegaParrotEntity animatable, long instanceId, AnimationState<MegaParrotEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        var head = this.getAnimationProcessor().getBone("head");

        var saddle = this.getAnimationProcessor().getBone("saddle");
        saddle.setHidden(!animatable.isSaddled());

        EntityModelData extraData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        if (head != null) {
            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        }
    }
}
