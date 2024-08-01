package website.skylorbeck.minecraft.megaparrot.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import website.skylorbeck.minecraft.megaparrot.Declarar;

public class MegaParrotArmorLayer extends GeoRenderLayer<MegaParrotEntity> {
    private static final Identifier MODEL =  Declarar.getMegaParrotId("geo/mega_parrot.geo.json");

    public MegaParrotArmorLayer(GeoRenderer<MegaParrotEntity> entityRendererIn) {
        super(entityRendererIn);
    }

	@Override
    public void render(MatrixStack poseStack, MegaParrotEntity megaParrot, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer bufferIn, float partialTicks, int packedLightIn, int packedOverlay) {
        ItemStack armorItemStack = megaParrot.getArmorType();
        if (!megaParrot.hasArmorInSlot() || !megaParrot.isHorseArmor(armorItemStack)) {
            return;
        }
        HorseArmorItem horseArmorItem = (HorseArmorItem)armorItemStack.getItem();
		RenderLayer cameo = RenderLayer.getArmorCutoutNoCull(Declarar.getMegaParrotId("textures/entity/armor/" + horseArmorItem.getEntityTexture().getPath().split("horse_armor_")[1]));
        float r = 1f;
        float g = 1f;
        float b = 1f;
        if (horseArmorItem instanceof DyeableHorseArmorItem) {
            int m = ((DyeableHorseArmorItem)horseArmorItem).getColor(armorItemStack);
            r = (float)(m >> 16 & 0xFF) / 255.0f;
            g = (float)(m >> 8 & 0xFF) / 255.0f;
            b = (float)(m & 0xFF) / 255.0f;
        }
        poseStack.push();
        //Move or scale the model as you see fit
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);

        GeoModel<MegaParrotEntity> geoModel = getGeoModel();
        this.getRenderer().actuallyRender(poseStack, megaParrot, geoModel.getBakedModel(geoModel.getModelResource(megaParrot)), cameo, bufferSource, bufferSource.getBuffer(cameo), true, partialTicks, packedLightIn, packedOverlay, r, g, b, 1.0f);
        poseStack.pop();
    }
}